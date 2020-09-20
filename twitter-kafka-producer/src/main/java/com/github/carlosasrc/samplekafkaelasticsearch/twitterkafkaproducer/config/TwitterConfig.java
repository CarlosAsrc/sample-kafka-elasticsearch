package com.github.carlosasrc.samplekafkaelasticsearch.twitterkafkaproducer.config;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.endpoint.StreamingEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
@PropertySource("classpath:application.yml")
public class TwitterConfig {

    @Value("${application.twitter.consumer-key}")
    private String consumerKey;
    @Value("${application.twitter.consumer-secret}")
    private String consumerSecret;
    @Value("${application.twitter.access-token}")
    private String accessToken;
    @Value("${application.twitter.access-token-secret}")
    private String accessTokenSecret;

    @Bean
    public BasicClient client() {
        return new ClientBuilder()
                .name("twitterStatusesFilterClient")
                .hosts(Constants.STREAM_HOST)
                .endpoint(getEndpoint())
                .authentication(getAuth())
                .processor(new StringDelimitedProcessor(linkedBlockingQueue()))
                .build();
    }

    @Bean
    public BlockingQueue<String> linkedBlockingQueue() {
        return  new LinkedBlockingQueue<String>(10000);
    }

    private Authentication getAuth() {
        return new OAuth1(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }

    private StreamingEndpoint getEndpoint() {
        StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
        endpoint.stallWarnings(false);
        endpoint.trackTerms(Lists.newArrayList("kafka", "software", "devops", "streaming"));
        return endpoint;
    }
}