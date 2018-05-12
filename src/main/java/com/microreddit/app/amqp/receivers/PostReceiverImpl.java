package com.microreddit.app.amqp.receivers;

import com.microreddit.app.amqp.listeners.PostListener;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Josh Harkema
 */
@Configuration
public class PostReceiverImpl {
    @Bean
    public Queue PostReceiver() {
        return new Queue("post-queue");
    }

    @Bean
    public PostListener postListener() {
        return new PostListener();
    }

    public String getName() {
        return "post-queue";
    }
}
