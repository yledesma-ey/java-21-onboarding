package com.cursojava.mspersonas.controller;

import com.cursojava.mspersonas.dto.response.ClientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonControllerAdviceTest {

    @InjectMocks
    private PersonControllerAdvice personControllerAdvice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleIllegalStateException_ShouldReturnBadRequest() {
        IllegalStateException exception = new IllegalStateException("Estado ilegal");

        ResponseEntity<ClientResponse> response = personControllerAdvice.handleIllegalStateException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Estado ilegal", response.getBody().getError().getError());
    }

    @Test
    void handleGeneralException_ShouldReturnInternalServerError() {
        Exception exception = new Exception("Error al dar de alta al cliente");

        ResponseEntity<ClientResponse> response = personControllerAdvice.handleGeneralException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al dar de alta al cliente", response.getBody().getError().getError());
    }
}
