package com.github.carlosasrc.samplekafkaelasticsearch.twitterkafkaproducer.messaging.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class TwitterKafkaProducer {

    @Value(value = "${application.kafka.topic-name}")
    private String topicName;

    private final KafkaTemplate<String, String> template;

    public void publish(String message) {
        try {
            template.send(topicName, message);
            log.info("Message sent to topic {}:", message);
        } catch (Exception e) {
            log.error("Error sending message to topic {}:", topicName, e);
        }
    }
}
