package com.cursojava.mscuentas.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cuentas")
public class Account {

    @Id
    @Column(name = "numcue")
    private Long numCuenta;

    @Column(name = "persnum")
    private Long persnum;

    @ManyToOne
    @JoinColumn(name = "divisa")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "estado")
    private AccountStatus estado;

    @Column(name = "saldo")
    private BigDecimal saldo;
}
