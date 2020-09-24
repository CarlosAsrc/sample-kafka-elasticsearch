package com.github.carlosasrc.samplekafkaelasticsearch.elasticsearchkafkaconsumer.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticsearchClient {

    private final RestHighLevelClient restHighLevelClient;

    public void saveIndex(IndexRequest indexRequest) {
        try {
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            log.info("New document indexed with id {}", indexResponse.getId());
        } catch (IOException exception) {
            log.error("Error indexing document {} :", indexRequest, exception);
        }
    }
}
