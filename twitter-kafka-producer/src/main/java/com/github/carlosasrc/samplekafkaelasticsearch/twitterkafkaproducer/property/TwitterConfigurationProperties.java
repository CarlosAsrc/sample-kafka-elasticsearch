package com.github.carlosasrc.samplekafkaelasticsearch.twitterkafkaproducer.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:application.yml")
public class TwitterConfigurationProperties {
    @Value("${twitter.consumer-key}")
    private String consumerKey;
    @Value("${twitter.consumer-secret}")
    private String consumerSecret;
    @Value("${twitter.access-token}")
    private String accessToken;
    @Value("${twitter.access-token-secret}")
    private String accessTokenSecret;
}
