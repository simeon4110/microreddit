package com.microreddit.app.amqp.receivers;

import com.microreddit.app.amqp.listeners.KarmaListener;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Josh Harkema
 */
@Configuration
public class KarmaReceiverImpl {
    @Bean
    public Queue KarmaReceiver() {
        return new Queue("karma-queue");
    }

    @Bean
    public KarmaListener karmaListener() {
        return new KarmaListener();
    }

    public String getName() {
        return "karma-queue";
    }

}
