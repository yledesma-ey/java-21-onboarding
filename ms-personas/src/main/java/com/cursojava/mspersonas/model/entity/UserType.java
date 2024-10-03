package com.cursojava.mspersonas.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_usuario")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipo_usuario")
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;
}
