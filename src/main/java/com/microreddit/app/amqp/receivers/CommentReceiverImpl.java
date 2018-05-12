package com.microreddit.app.amqp.receivers;

import com.microreddit.app.amqp.listeners.CommentListener;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Josh Harkema
 */
@Configuration
public class CommentReceiverImpl {
    @Bean
    public Queue CommentReceiver() {
        return new Queue("comment-queue");
    }

    @Bean
    public CommentListener commentListener() {
        return new CommentListener();
    }

    public String getName() {
        return "comment-queue";
    }
}
