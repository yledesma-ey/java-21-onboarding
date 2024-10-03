package com.cursojava.mspersonas.client.service;

import com.cursojava.mspersonas.client.VerazClient;
import com.cursojava.mspersonas.client.response.VerazResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class VerazService {

    private final VerazClient verazClient;

    public VerazService(VerazClient verazClient) {
        this.verazClient = verazClient;
    }

    public CompletableFuture<Double> getScore(String dni) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                VerazResponse verazResponse = verazClient.getScore(dni);
                log.info("Response from veraz DNI: {}, {}", dni, verazResponse);
                if (verazResponse.getResponse() != null && !verazResponse.getResponse().isEmpty()) {
                    return verazResponse.getResponse().get(0).getScore();
                }
            } catch (Exception e) {
                log.error("Error al comunicarse con WorldSys: ", e);
            }
            return 0.0;
        });
    }
}

