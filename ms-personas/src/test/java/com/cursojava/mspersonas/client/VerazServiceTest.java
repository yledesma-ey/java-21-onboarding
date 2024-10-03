package com.cursojava.mspersonas.client;

import com.cursojava.mspersonas.client.data.VerazData;
import com.cursojava.mspersonas.client.response.VerazResponse;
import com.cursojava.mspersonas.client.service.VerazService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class VerazServiceTest {

    @InjectMocks
    private VerazService verazService;

    @Mock
    private VerazClient verazClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getScore_ShouldReturnValidScore_WhenResponseIsNotEmpty() {
        VerazResponse mockResponse = new VerazResponse();
        mockResponse.setResponse(Collections.singletonList(new VerazData(12345678, 0.1)));

        when(verazClient.getScore("12345678")).thenReturn(mockResponse);

        Double result = verazService.getScore("12345678").join();

        assertEquals(0.1, result);
    }

    @Test
    void getScore_ShouldReturnZero_WhenResponseIsEmpty() throws Exception {
        VerazResponse mockResponse = new VerazResponse();
        mockResponse.setResponse(Collections.emptyList());

        when(verazClient.getScore("12345678")).thenReturn(mockResponse);

        Double result = verazService.getScore("12345678").join();

        assertEquals(0.0, result);
    }

    @Test
    void getScore_ShouldReturnZero_WhenClientThrowsException() throws Exception {
        when(verazClient.getScore("12345678")).thenThrow(new RuntimeException("Service error"));

        Double result = verazService.getScore("dni").join();

        assertEquals(0.0, result);
    }
}
