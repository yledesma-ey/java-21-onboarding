package com.cursojava.mstarjetas.service;

import com.cursojava.mstarjetas.model.entity.CardStatus;
import com.cursojava.mstarjetas.repository.CardStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class CardStatusService {

    private final CardStatusRepository cardStatusRepository;

    public CardStatusService(CardStatusRepository cardStatusRepository) {
        this.cardStatusRepository = cardStatusRepository;
    }

    public CardStatus findById(Long id) {
        return cardStatusRepository.findById(id).orElse(null);
    }
}
