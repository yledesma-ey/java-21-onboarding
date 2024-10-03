package com.cursojava.mstarjetas.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class IssuedSummaryId implements Serializable {
    @Column(name = "id")
    private Long id;

    @Column(name = "resumeMongoID")
    private String resumeMongoID;
}
