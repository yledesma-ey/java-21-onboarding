package com.cursojava.mscuentas.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "codigo_moneda")
public class Currency {

    @Id
    @Column(name = "cod_moneda")
    private Long code;

    @Column(name = "pais")
    private String pais;

    @Column(name = "simbolo")
    private String symbol;
}
