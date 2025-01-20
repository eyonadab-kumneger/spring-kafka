package com.example.kafka.service.producer;


import com.example.kafka.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStringMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
        System.out.println("Sent String message: " + message);
    }

    public void sendJsonMessage(String topic, Book payload) {
        kafkaTemplate.send(topic, payload);
        System.out.println("Sent JSON message: " + payload);
    }
}

