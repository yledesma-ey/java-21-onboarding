package com.cursojava.mstarjetas.service;

import com.cursojava.mstarjetas.model.entity.IssuedSummary;
import com.cursojava.mstarjetas.model.entity.IssuedSummaryId;
import com.cursojava.mstarjetas.repository.IssuedSummaryRepository;
import com.cursojava.mstarjetas.utils.DateUtils;
import com.cursojava.mstarjetas.utils.SummaryGenerator;
import org.springframework.stereotype.Service;

import java.util.stream.DoubleStream;

@Service
public class IssuedSummaryService {

    private final IssuedSummaryRepository issuedSummaryRepository;

    public IssuedSummaryService(IssuedSummaryRepository issuedSummaryRepository) {
        this.issuedSummaryRepository = issuedSummaryRepository;
    }

    public void createIssuedSummary(Long id) {
        IssuedSummaryId issuedSummaryId = new IssuedSummaryId(null, SummaryGenerator.generateRandomString());
        IssuedSummary issuedSummary = IssuedSummary
                .builder()
                .id(issuedSummaryId)
                .totalPesos(10245.01)
                .totalDolares(1200.00)
                .fechaVencimiento(DateUtils.getEndOfMonthDate())
                .build();

        issuedSummaryRepository.save(issuedSummary);
    }
}
