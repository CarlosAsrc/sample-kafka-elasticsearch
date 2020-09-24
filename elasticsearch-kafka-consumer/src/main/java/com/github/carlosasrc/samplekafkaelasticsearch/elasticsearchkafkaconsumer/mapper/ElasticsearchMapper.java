package com.github.carlosasrc.samplekafkaelasticsearch.elasticsearchkafkaconsumer.mapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchMapper {

    @Value("${application.elasticsearch.server.index}")
    private String index;

    public IndexRequest toIndexRequest(ConsumerRecord<String, String> record) {
        return new IndexRequest(index)
                .source(record.value(), XContentType.JSON)
                .id(buildId(record));
    }

    private String buildId(ConsumerRecord<String, String> record) {
        return record.topic() + record.partition() + record.offset();
    }
}
