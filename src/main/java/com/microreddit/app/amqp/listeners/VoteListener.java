package com.microreddit.app.amqp.listeners;

import com.microreddit.app.amqp.messages.VoteMessage;
import org.springframework.stereotype.Component;

/**
 * @author Josh Harkema
 */
@Component
public class VoteListener {
    public void receiveMessage(final VoteMessage message) {
        System.out.println(" [x] Received '" + message.toString() + "'");
    }
}
