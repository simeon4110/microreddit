package com.microreddit.app.amqp.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author Josh Harkema
 */
@RabbitListener(queues = "karma-queue")
public class KarmaListener {
    @RabbitHandler
    public void receive(String in) {
        System.out.println(" [x] Received '" + in + "'");
    }
}
