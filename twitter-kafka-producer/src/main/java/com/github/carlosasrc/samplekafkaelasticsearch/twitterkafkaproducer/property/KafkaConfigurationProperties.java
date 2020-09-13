package com.github.carlosasrc.samplekafkaelasticsearch.twitterkafkaproducer.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:application.yml")
public class KafkaConfigurationProperties {
    @Value(value = "${kafka.bootstrap-address}")
    private String bootstrapAddress;

    @Value(value = "${kafka.topic-name}")
    private String topicName;
}
