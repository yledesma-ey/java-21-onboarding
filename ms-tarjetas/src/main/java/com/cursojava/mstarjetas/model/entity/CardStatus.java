package com.cursojava.mstarjetas.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estado_tarjeta")
public class CardStatus {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "detalle")
    private String detail;
}
