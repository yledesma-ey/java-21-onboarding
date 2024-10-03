package com.cursojava.mspersonas.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persnum")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastName;

    @Column(name = "dni")
    private String dni;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado", referencedColumnName = "idestado_usuario")
    private UserStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo", referencedColumnName = "idtipo_usuario")
    private UserType type;
}
