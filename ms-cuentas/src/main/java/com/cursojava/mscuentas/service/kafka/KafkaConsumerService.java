package com.cursojava.mscuentas.service.kafka;

import com.cursojava.mscuentas.dto.AccountMessage;
import com.cursojava.mscuentas.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    private final AccountService accountService;

    public KafkaConsumerService(AccountService accountService) {
        this.accountService = accountService;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group.id}")
    public void consume(AccountMessage accountMessage) {
        log.info("Mensaje recibido: id:{}, dni: {}",accountMessage.getPersnum(), accountMessage.getProduct());
        accountService.processAccountCreation(accountMessage.getPersnum(), accountMessage.getProduct());
    }
}