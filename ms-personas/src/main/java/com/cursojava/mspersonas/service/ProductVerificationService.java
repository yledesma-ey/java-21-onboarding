package com.cursojava.mspersonas.service;

import com.cursojava.mspersonas.client.service.RenaperService;
import com.cursojava.mspersonas.client.service.VerazService;
import com.cursojava.mspersonas.client.service.WorldsysService;
import com.cursojava.mspersonas.dto.AccountMessage;
import com.cursojava.mspersonas.dto.CardMessage;
import com.cursojava.mspersonas.rules.DecisionMatrix;
import com.cursojava.mspersonas.rules.DecisionRule;
import com.cursojava.mspersonas.service.kafka.KafkaSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Slf4j
@Service
public class ProductVerificationService {

    private final RenaperService renaperService;

    private final VerazService verazService;

    private final WorldsysService worldsysService;

    private final ObjectMapper objectMapper;

    private final KafkaSender kafkaSender;

    private final DecisionMatrix decisionMatrix = new DecisionMatrix();

    public ProductVerificationService(RenaperService renaperService, VerazService verazService, WorldsysService worldsysService, ObjectMapper objectMapper, KafkaSender kafkaSender) {
        this.renaperService = renaperService;
        this.verazService = verazService;
        this.worldsysService = worldsysService;
        this.objectMapper = objectMapper;
        this.kafkaSender = kafkaSender;
    }

    public void determineProduct(Long persnum, String dni, BigDecimal grossSalary) {
        log.info("Determining product for persnum: " + persnum + ", dni: " + dni + ", grossSalary: " + grossSalary);

        CompletableFuture<Boolean> isAuthorizedFuture = renaperService.isAuthorized(dni);
        CompletableFuture<Boolean> isTerroristFuture = worldsysService.isTerrorist(dni);
        CompletableFuture<Double> verazScoreFuture = verazService.getScore(dni);

        CompletableFuture.allOf(isAuthorizedFuture, isTerroristFuture, verazScoreFuture)
                .thenCompose(v -> {
                    boolean isAuthorized = isAuthorizedFuture.join();
                    boolean isTerrorist = isTerroristFuture.join();
                    Double verazScore = verazScoreFuture.join();
                    log.info("isAuthorized: {}, isTerrorist: {}, verazScore: {}", isAuthorized, isTerrorist, verazScore);

                    DecisionRule rule = decisionMatrix.getRule(isAuthorized, isTerrorist, verazScore, grossSalary.doubleValue());
                    if (rule != null) {
                        log.info("Matched rule: " + rule.getProduct());
                        log.info("Matched card: {}", rule.getTarjeta());
                        return sendMessageToCuentas(rule.getProduct(), persnum)
                                .thenCompose(v2 -> {
                                    if (rule.getTarjeta() != null) {
                                        return sendMessageToTarjetas(rule.getTarjeta(), persnum);
                                    } else {
                                        return CompletableFuture.completedFuture(null);
                                    }
                                });
                    } else {
                        log.info("No matching rule found");
                        return CompletableFuture.completedFuture(null);
                    }
                })
                .exceptionally(e -> {
                    log.error("Error during product determination: " + e.getMessage());
                    return null;
                });
    }

    private CompletableFuture<Void> sendMessageToCuentas(String product, Long persnum) {
        try {
            String message = objectMapper.writeValueAsString(new AccountMessage(product, persnum));
            return CompletableFuture.supplyAsync(() -> {
                try {
                    kafkaSender.sendMessage("cuenta-topic", "key", message).get();
                    return null;
                } catch (Exception e) {
                    throw new CompletionException(e);
                }
            });
        } catch (Exception e) {
            log.error("Error sending message to Cuentas Kafka", e);
            return CompletableFuture.failedFuture(e);
        }
    }


    private CompletableFuture<Void> sendMessageToTarjetas(String tarjeta, Long persnum) {
        try {
            String message = objectMapper.writeValueAsString(new CardMessage(tarjeta, persnum));
            return CompletableFuture.supplyAsync(() -> {
                try {
                    kafkaSender.sendMessage("tarjeta-topic", "key", message).get();
                    return null;
                } catch (Exception e) {
                    log.error("Error sending message to Tarjetas Kafka", e);
                    throw new CompletionException(e);
                }
            });
        } catch (Exception e) {
            log.error("Error sending message to Tarjetas Kafka", e);
            return CompletableFuture.failedFuture(e);
        }
    }

}
