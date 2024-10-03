package com.cursojava.mstarjetas.service.kafka;

import com.cursojava.mstarjetas.dto.CardMessage;
import com.cursojava.mstarjetas.service.CardProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Service
@Slf4j
public class KafkaConsumerService {
    private final CardProductService cardProductService;

    public KafkaConsumerService(CardProductService cardProductService) {
        this.cardProductService = cardProductService;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group.id}")
    public void consume(CardMessage cardMessage) {
        log.info("Processed tarjeta: {}, numPersona: {}" , cardMessage.getCard() , cardMessage.getPersum());
        cardProductService.createCardProduct(cardMessage.getCard(), cardMessage.getPersum());
    }
}
