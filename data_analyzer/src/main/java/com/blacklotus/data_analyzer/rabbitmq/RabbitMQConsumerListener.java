package com.blacklotus.data_analyzer.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.blacklotus.data_analyzer.config.RabbitMQConfig.QUEUE;

@Component
public class RabbitMQConsumerListener {

    @RabbitListener(queues = QUEUE)
    public void receive(String message) {
        System.out.println("Received from RabbitMQ: " + message);
    }
}
