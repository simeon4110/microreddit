package com.microreddit.app.message;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Basic receiver for testing rabbitMQ.
 *
 * @author Josh Harkema
 */
@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
