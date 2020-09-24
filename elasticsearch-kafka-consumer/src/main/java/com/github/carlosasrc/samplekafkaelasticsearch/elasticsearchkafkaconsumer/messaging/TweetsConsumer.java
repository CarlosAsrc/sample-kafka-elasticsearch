package com.github.carlosasrc.samplekafkaelasticsearch.elasticsearchkafkaconsumer.messaging;

import com.github.carlosasrc.samplekafkaelasticsearch.elasticsearchkafkaconsumer.client.ElasticsearchClient;
import com.github.carlosasrc.samplekafkaelasticsearch.elasticsearchkafkaconsumer.mapper.ElasticsearchMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TweetsConsumer {

    private final ElasticsearchClient elasticsearchClient;
    private final ElasticsearchMapper elasticsearchMapper;

    @KafkaListener(topics= "${application.kafka.topic.tweets}")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            elasticsearchClient.saveIndex(elasticsearchMapper.toIndexRequest(record));
        } catch (Exception e) {
            log.error("Error while consuming message from tweets topic:", e);
        }
    }
}
