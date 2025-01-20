package com.consumer.kafka.controller;


import com.consumer.kafka.model.Book;
import com.consumer.kafka.service.producer.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private final KafkaProducerService producerService;

    @Autowired
    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/publish/string")
    public String sendStringMessage(@RequestParam String message) {
        producerService.sendStringMessage("test-topic", message);
        return "String message sent successfully!";
    }

    @PostMapping("/publish/json")
    public String sendJsonMessage(@RequestBody Book payload) {
        producerService.sendJsonMessage("books-topic", payload);
        return "JSON message sent successfully!";
    }
}
