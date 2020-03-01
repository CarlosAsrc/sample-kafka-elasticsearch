package com.github.carlosasrc.samplekafkaelasticsearch.twittermicroservice;

import com.twitter.hbc.httpclient.BasicClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class TwitterStreamingService {

    @Autowired
    private BasicClient client;
    @Autowired
    private BlockingQueue<String> queue;

    private String msg;

    public void listen() throws InterruptedException {
        try {
            client.connect();
            if (client.isDone()) {
                log.info("Client connection closed unexpectedly: " + client.getExitEvent().getMessage());
            }
            msg = queue.poll(5, TimeUnit.SECONDS);
            if (msg == null) {
                log.info("Did not receive a message in 5 seconds");
            } else {
                log.info(msg);
            }

        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            client.stop();
            log.info("The client read %d messages!\n", client.getStatsTracker().getNumMessages());
        }
    }
}
