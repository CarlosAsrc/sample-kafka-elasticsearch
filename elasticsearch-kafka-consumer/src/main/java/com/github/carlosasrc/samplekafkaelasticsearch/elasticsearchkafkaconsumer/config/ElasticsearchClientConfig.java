package com.github.carlosasrc.samplekafkaelasticsearch.elasticsearchkafkaconsumer.config;


import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class ElasticsearchClientConfig {

    @Value("${application.elasticsearch.auth.access-key}")
    private String accessKey;
    @Value("${application.elasticsearch.auth.access-token}")
    private String accessToken;
    @Value("${application.elasticsearch.server.hostname}")
    private String hostname;
    @Value("${application.elasticsearch.server.port}")
    private int port;


    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(getRestClientBuilder());
    }

    public RestClientBuilder getRestClientBuilder() {
        return RestClient.builder(new HttpHost(hostname, port, "https"))
                .setHttpClientConfigCallback(getHttpClientConfigCallback());
    }

    private RestClientBuilder.HttpClientConfigCallback getHttpClientConfigCallback() {
        return httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(getCredentialsProvider());
    }

    private CredentialsProvider getCredentialsProvider() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(accessKey, accessToken));
        return credentialsProvider;
    }
}