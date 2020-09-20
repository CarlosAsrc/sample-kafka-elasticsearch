package com.github.carlosasrc.samplekafkaelasticsearch.twitterkafkaproducer.messaging.producer;

import com.github.carlosasrc.samplekafkaelasticsearch.twitterkafkaproducer.config.KafkaConfigurationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TwitterKafkaProducer {

    private final KafkaTemplate<String, String> template;
    private final KafkaConfigurationProperties kafkaConfigurationProperties;

    public void publish(String message) {
        try {
            template.send(kafkaConfigurationProperties.getTopicName(), message);
            log.info("Message sent to topic {}:", message);
        } catch (Exception e) {
            log.error("Error sending message to topic {}:", kafkaConfigurationProperties.getTopicName(), e);
        }
    }
}
