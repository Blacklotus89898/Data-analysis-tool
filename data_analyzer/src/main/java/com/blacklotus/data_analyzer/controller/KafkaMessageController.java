package com.blacklotus.data_analyzer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blacklotus.data_analyzer.kafka.KafkaProducerService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/kafka")
public class KafkaMessageController {

    private final KafkaProducerService producerService;

    public KafkaMessageController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send")
    public Mono<String> sendMessage(@RequestBody Mono<String> messageMono) {
        return messageMono.map(message -> {
            producerService.sendMessage(message);
            return "Message sent to Kafka: " + message;
        });
    }
}
