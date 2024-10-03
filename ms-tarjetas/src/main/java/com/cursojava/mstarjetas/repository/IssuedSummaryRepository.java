package com.cursojava.mstarjetas.repository;

import com.cursojava.mstarjetas.model.entity.IssuedSummary;
import com.cursojava.mstarjetas.model.entity.IssuedSummaryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedSummaryRepository extends JpaRepository<IssuedSummary, IssuedSummaryId> {
}
