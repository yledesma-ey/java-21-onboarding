package com.cursojava.mspersonas.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    private String name;
    private String lastName;
    private String dni;
    private BigDecimal salary;
    private Long userType;

}
