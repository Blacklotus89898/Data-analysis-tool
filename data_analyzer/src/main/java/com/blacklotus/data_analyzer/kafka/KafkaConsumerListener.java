package com.blacklotus.data_analyzer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaListener(topics = "demo-topic", groupId = "demo-group")
    public void listen(String message) {
      logger.info("### Received: {} ###", message);
    }
}
