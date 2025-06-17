package com.blacklotus.data_analyzer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blacklotus.data_analyzer.rabbitmq.RabbitMQProducerService;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQMessageController {

    private final RabbitMQProducerService producer;

    public RabbitMQMessageController(RabbitMQProducerService producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public String send(@RequestBody String message) {
        producer.send(message);
        return "Sent: " + message;
    }
}
