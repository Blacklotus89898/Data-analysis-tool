package com.blacklotus.data_analyzer.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class STOMPMessageController {

    private static final Logger logger = LoggerFactory.getLogger(STOMPMessageController.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Mono<String> broadcastMessage(String message) {
        logger.info("### Received: {} ###", message);
        return Mono.fromCallable(() -> {
            redisTemplate.opsForList().leftPush("messages", message);
            return message;
        });
    }

    @GetMapping("/api/messages")
    public Mono<List<String>> getCachedMessages(@RequestParam(defaultValue = "10") int count) {
        return Mono.fromCallable(() -> {
            List<String> messages = redisTemplate.opsForList().range("messages", 0, count - 1);
            return messages != null ? messages : Collections.emptyList();
        });
    }
}
