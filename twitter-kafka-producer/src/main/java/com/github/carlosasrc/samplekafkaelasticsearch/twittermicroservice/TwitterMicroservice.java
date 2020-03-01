package com.github.carlosasrc.samplekafkaelasticsearch.twittermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class TwitterMicroservice {
    public static void main(String[] args) {
        SpringApplication.run(TwitterMicroservice.class, args);
    }
}
