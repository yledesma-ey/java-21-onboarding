package com.cursojava.mstarjetas.service.kafka;

import com.cursojava.mstarjetas.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class KafkaConsumerServiceTest {

    @InjectMocks
    private KafkaConsumerService kafkaConsumerService;

    @Mock
    private CardService cardProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


}
