package com.cursojava.mspersonas.client.service;

import com.cursojava.mspersonas.client.RenaperClient;
import com.cursojava.mspersonas.client.response.RenaperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class RenaperService {

    private final RenaperClient renaperClient;

    public RenaperService(RenaperClient renaperClient) {
        this.renaperClient = renaperClient;
    }

    public CompletableFuture<Boolean> isAuthorized(String dni) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                RenaperResponse renaperResponse = renaperClient.isAuthorize(dni);
                log.info("Response from Renaper DNI: {}, {}", dni, renaperResponse);
                if (renaperResponse.getResponse() != null && !renaperResponse.getResponse().isEmpty()) {
                    return renaperResponse.getResponse().get(0).getIsAuthorize();
                }
                return false;
            } catch (Exception e) {
                log.error("Error al obtener autorización de Renaper", e);
                return false;
            }
        });
    }
}