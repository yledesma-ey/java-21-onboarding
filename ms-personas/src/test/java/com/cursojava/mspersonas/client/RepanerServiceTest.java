package com.cursojava.mspersonas.client;

import com.cursojava.mspersonas.client.data.RenaperData;
import com.cursojava.mspersonas.client.response.RenaperResponse;
import com.cursojava.mspersonas.client.service.RenaperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RepanerServiceTest {

    @InjectMocks
    private RenaperService renaperService;

    @Mock
    private RenaperClient renaperClient;

    private final String dni = "12345678";  // DNI para usar en las pruebas

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isAuthorized_ShouldReturnTrue_WhenResponseHasAuthorization() throws Exception {
        RenaperResponse mockResponse = new RenaperResponse();
        mockResponse.setResponse(Collections.singletonList(new RenaperData(123456789, true)));

        when(renaperClient.isAuthorize(dni)).thenReturn(mockResponse);

        CompletableFuture<Boolean> result = renaperService.isAuthorized(dni);

        assertTrue(result.join());
    }

    @Test
    void isAuthorized_ShouldReturnFalse_WhenResponseIsEmpty() throws Exception {
        RenaperResponse mockResponse = new RenaperResponse();
        mockResponse.setResponse(Collections.emptyList());

        when(renaperClient.isAuthorize(dni)).thenReturn(mockResponse);

        // Cuando (Act)
        CompletableFuture<Boolean> result = renaperService.isAuthorized(dni);

        // Entonces (Assert)
        assertFalse(result.join());  // join() bloquea hasta obtener el resultado
    }

    @Test
    void isAuthorized_ShouldReturnFalse_WhenClientThrowsException() throws Exception {
        // Dado (Arrange)
        when(renaperClient.isAuthorize(dni)).thenThrow(new RuntimeException("Service error"));

        // Cuando (Act)
        CompletableFuture<Boolean> result = renaperService.isAuthorized(dni);

        // Entonces (Assert)
        assertFalse(result.join());  // Debería devolver false cuando ocurre una excepción
    }
}

