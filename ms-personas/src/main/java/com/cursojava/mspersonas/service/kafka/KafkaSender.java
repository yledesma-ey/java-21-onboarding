package com.cursojava.mspersonas.service.kafka;

import com.cursojava.mspersonas.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

@Slf4j
public class KafkaSender {

    private final KafkaProducer<String, String> kafkaProducer;

    public KafkaSender(Properties properties) {
        this.kafkaProducer = new KafkaProducer<>(properties);
    }

    public Future<RecordMetadata> sendMessage(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        return kafkaProducer.send(record);
    }

    public void close() {
        kafkaProducer.close();
    }
}