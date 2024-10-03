package com.cursojava.mspersonas.controller;

import com.cursojava.mspersonas.dto.response.ClientResponse;
import com.cursojava.mspersonas.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class PersonControllerAdvice {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ClientResponse> handleIllegalStateException(IllegalStateException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(ex.getMessage())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .build();

        ClientResponse clientResponse = ClientResponse.builder()
                .error(errorResponse)
                .build();

        return new ResponseEntity<>(clientResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ClientResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Error al dar de alta al cliente")
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build();

        ClientResponse clientResponse = ClientResponse.builder()
                .error(errorResponse)
                .build();

        return new ResponseEntity<>(clientResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
