package com.microreddit.app.amqp.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author Josh Harkema
 */
@RabbitListener(queues = "post-queue")
public class PostListener {
    @RabbitHandler
    public void receive(String in) {
        System.out.println(" [x] Received '" + in + "'");
    }
}
