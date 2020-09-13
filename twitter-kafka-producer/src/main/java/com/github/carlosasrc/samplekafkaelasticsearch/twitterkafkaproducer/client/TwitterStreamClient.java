package com.github.carlosasrc.samplekafkaelasticsearch.twitterkafkaproducer.client;

import com.github.carlosasrc.samplekafkaelasticsearch.twitterkafkaproducer.messaging.producer.TwitterKafkaProducer;
import com.twitter.hbc.httpclient.BasicClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class TwitterStreamClient {

    private final BasicClient client;
    private final TwitterKafkaProducer twitterKafkaProducer;
    private final BlockingQueue<String> queue;

    private String message;

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        try {
            client.connect();
            while(!client.isDone()){
                message = queue.poll(5, TimeUnit.SECONDS);
                if (message == null) {
                    log.info("Did not receive a message in 5 seconds");
                } else {
                    twitterKafkaProducer.publish(message);
                }
            }
        } catch (InterruptedException error) {
            log.info("Client connection closed unexpectedly: " + client.getExitEvent().getMessage());
        } finally {
            client.stop();
            log.info("The client read {} messages!\n", client.getStatsTracker().getNumMessages());
        }
    }
}
