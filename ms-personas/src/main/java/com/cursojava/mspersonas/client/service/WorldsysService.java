package com.cursojava.mspersonas.client.service;

import com.cursojava.mspersonas.client.WorldsysClient;
import com.cursojava.mspersonas.client.response.WorldSysResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class WorldsysService {
    private final WorldsysClient worldsysClient;

    public WorldsysService(WorldsysClient worldsysClient) {
        this.worldsysClient = worldsysClient;
    }


    public CompletableFuture<Boolean> isTerrorist(String dni) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                WorldSysResponse worldSysResponse = worldsysClient.isTerrotist(dni);
                log.info("Response from WorldSys DNI: {}, {}", dni, worldSysResponse);
                if (worldSysResponse.getResponse() != null && !worldSysResponse.getResponse().isEmpty()) {
                    return worldSysResponse.getResponse().get(0).getIsTerrotist();
                }
                return false;
            } catch (Exception e) {
                log.error("Error al comunicarse con WorldSys: ", e);
                return false;
            }
        });
    }
}
