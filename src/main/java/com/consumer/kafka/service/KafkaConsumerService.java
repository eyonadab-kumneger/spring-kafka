package com.consumer.kafka.service.consumer;

import com.consumer.kafka.model.Book;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "group_id")
    public void consumeStringMessage(String message) {
        System.out.println("Consumed String message: " + message);
    }

    @KafkaListener(topics = "books-topic", groupId = "group_id",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeJsonMessage(Book message) {
        System.out.println("Consumed JSON message: " + message);
    }
}

