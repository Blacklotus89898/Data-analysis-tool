package com.blacklotus.data_analyzer.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, topics = { "demo-topic" })
class KafkaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private KafkaConsumerListener kafkaConsumerListener;

    @Test
    void testKafkaSendAndReceive() throws Exception {
        String testMessage = "Hello Kafka!";

        mockMvc.perform(post("/kafka/send")
                        .content(testMessage)
                        .contentType("text/plain"))
                .andExpect(status().isOk());

        // Verify listener was invoked (may need to increase timeout)
        verify(kafkaConsumerListener, timeout(5000)).listen(testMessage);
    }
}
