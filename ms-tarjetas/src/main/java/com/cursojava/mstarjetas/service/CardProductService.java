package com.cursojava.mstarjetas.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardProductService {

    private final CardService cardService;

    private final IssuedSummaryService issuedSummaryService;

    public CardProductService(CardService cardService, IssuedSummaryService issuedSummaryService) {
        this.cardService = cardService;
        this.issuedSummaryService = issuedSummaryService;
    }

    public void createCardProduct(String cardName, Long numCuenta) {
        log.info("Creating card product for cardName: " + cardName + ", numCuenta: " + numCuenta);
        cardService.createCardProduct(cardName, numCuenta);
        log.info("Creating issued summary for numCuenta: " + numCuenta);
        issuedSummaryService.createIssuedSummary(numCuenta);
    }
}
