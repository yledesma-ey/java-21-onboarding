package com.cursojava.mstarjetas.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarjetas")
public class Card {

    @Id
    @Column(name = "numtarj")
    private String numtarj;

    @Column(name = "numcue")
    private Long numcue;

    @Column(name = "f_vencimiento")
    private String fVencimiento;

    @Column(name = "pin")
    private Long pin;

    @ManyToOne
    @JoinColumn(name = "estado")
    private CardStatus estado;

    @Column(name = "f_emision")
    private String fEmision;

    @Column(name = "tipo")
    private String tipo;


}
