package com.cursojava.mstarjetas.service;

import com.cursojava.mstarjetas.model.entity.Card;
import com.cursojava.mstarjetas.repository.CardRepository;
import com.cursojava.mstarjetas.utils.CardCreditGenerate;
import com.cursojava.mstarjetas.utils.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    private final CardStatusService cardStatusService;

    public CardService(CardRepository cardRepository, CardStatusService cardStatusService) {
        this.cardRepository = cardRepository;
        this.cardStatusService = cardStatusService;
    }

    public void createCardProduct(String cardName, Long numCuenta) {
        Card card = Card
                .builder()
                .numtarj(CardCreditGenerate.generateCardNumber())
                .numcue(numCuenta)
                .fVencimiento(DateUtils.getExpirationDate())
                .pin(CardCreditGenerate.generateCVV())
                .estado(cardStatusService.findById(1L))
                .fEmision(DateUtils.getCurrentMonthYear())
                .tipo(CardCreditGenerate.getCardType(cardName))
                .build();

        cardRepository.save(card);
    }
}
