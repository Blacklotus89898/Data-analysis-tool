package com.blacklotus.data_analyzer.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(partitions = 1, topics = { "demo-topic" })
class KafkaIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private KafkaConsumerListener kafkaConsumerListener;

    @Test
    void testKafkaSendAndReceive() {
        String testMessage = "Hello Kafka!";

        webTestClient.post()
                .uri("/kafka/send")
                .bodyValue(testMessage)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Message sent to Kafka: " + testMessage);

        // Verify listener was invoked (may need to increase timeout)
        verify(kafkaConsumerListener, timeout(5000)).listen(testMessage);
    }
}
