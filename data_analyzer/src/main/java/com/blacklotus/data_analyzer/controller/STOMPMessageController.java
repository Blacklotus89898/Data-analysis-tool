package com.blacklotus.data_analyzer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class STOMPMessageController {

    private static final Logger logger = LoggerFactory.getLogger(STOMPMessageController.class);

    @MessageMapping("/send") // listens to /app/send
    @SendTo("/topic/messages") //broadcast to this topic
    public String broadcastMessage(String message) {
        logger.info("### Received: {} ###", message);
        return message;
    }
}
