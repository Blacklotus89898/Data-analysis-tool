package com.blacklotus.data_analyzer.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.blacklotus.data_analyzer.config.RabbitMQConfig.EXCHANGE;

@Service
public class RabbitMQProducerService {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(EXCHANGE, "demo.routing.key", message);
    }
}
