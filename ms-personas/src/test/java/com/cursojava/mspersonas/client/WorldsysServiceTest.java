package com.cursojava.mspersonas.client;

import com.cursojava.mspersonas.client.data.WorldSysData;
import com.cursojava.mspersonas.client.response.WorldSysResponse;
import com.cursojava.mspersonas.client.service.WorldsysService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class WorldsysServiceTest {

    @InjectMocks
    private WorldsysService worldsysService;

    @Mock
    private WorldsysClient worldsysClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsTerrorist_WhenResponseIsTerrorist() {
        String dni = "12345678";
        WorldSysResponse response = new WorldSysResponse();
        response.setResponse(Collections.singletonList(new WorldSysData(12345678, true)));

        when(worldsysClient.isTerrotist(dni)).thenReturn(response);

        CompletableFuture<Boolean> result = worldsysService.isTerrorist(dni);

        assertTrue(result.join());
    }

    @Test
    void testIsTerrorist_WhenResponseIsNotTerrorist() {
        String dni = "12345678";
        WorldSysResponse response = new WorldSysResponse();
        response.setResponse(List.of(new WorldSysData(12345678, false)));
        when(worldsysClient.isTerrotist(dni)).thenReturn(response);

        CompletableFuture<Boolean> result = worldsysService.isTerrorist(dni);

        assertFalse(result.join());

    }

    @Test
    void testIsTerrorist_WhenResponseIsEmpty() {
        String dni = "12345678";
        WorldSysResponse response = new WorldSysResponse();
        response.setResponse(Collections.emptyList());

        when(worldsysClient.isTerrotist(dni)).thenReturn(response);

        CompletableFuture<Boolean> result = worldsysService.isTerrorist(dni);

        assertFalse(result.join());
    }

    @Test
    void testIsTerrorist_WhenExceptionThrown() {
        String dni = "12345678";
        when(worldsysClient.isTerrotist(dni)).thenThrow(new RuntimeException("Service error"));

        CompletableFuture<Boolean> result = worldsysService.isTerrorist(dni);

        assertFalse(result.join());
    }
}
