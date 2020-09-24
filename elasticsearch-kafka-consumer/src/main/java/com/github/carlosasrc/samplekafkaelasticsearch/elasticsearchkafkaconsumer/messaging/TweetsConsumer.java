package com.github.carlosasrc.samplekafkaelasticsearch.elasticsearchkafkaconsumer.messaging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TweetsConsumer {

    @KafkaListener(topics= "${aplication.kafka.topic.tweets}")
    public void listen(ConsumerRecord<String, String> record) {
        try {

        } catch (Exception e) {
            log.error("Error while consuming message from tweets topic:", e);
        }
    }
}
