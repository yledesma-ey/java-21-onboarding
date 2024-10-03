package com.cursojava.mstarjetas.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resumen_emitidos")
public class IssuedSummary {
    @EmbeddedId
    private IssuedSummaryId id;

    @Column(name = "total_pesos")
    private Double totalPesos;

    @Column(name = "total_dolares")
    private Double totalDolares;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;
}
