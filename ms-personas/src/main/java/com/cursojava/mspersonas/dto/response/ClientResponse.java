package com.cursojava.mspersonas.dto.response;

import com.cursojava.mspersonas.model.error.ErrorResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientResponse {
    private Long id;
    private String message;
    private ErrorResponse error;
}
