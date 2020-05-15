package com.github.carlosasrc.samplekafkaelasticsearch.twitterkafkaproducer.client;

import com.twitter.hbc.httpclient.BasicClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class TwitterStreamClient {

    @Autowired
    private BasicClient client;
    @Autowired
    private BlockingQueue<String> queue;

    private String msg;

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        try {
            client.connect();
            while(!client.isDone()){
                msg = queue.poll(5, TimeUnit.SECONDS);
                if (msg == null) {
                    log.info("Did not receive a message in 5 seconds");
                } else {
                    log.info(msg);
                }
            }
        } catch (InterruptedException error) {
            log.info("Client connection closed unexpectedly: " + client.getExitEvent().getMessage());
        } finally {
            client.stop();
            log.info("The client read %d messages!\n", client.getStatsTracker().getNumMessages());
        }
    }
}
