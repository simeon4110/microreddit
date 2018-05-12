package com.microreddit.app.amqp.listeners;

import com.microreddit.app.amqp.messages.VoteMessage;
import com.microreddit.app.services.PostDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Josh Harkema
 */
@Component
public class VoteListener {
    private final PostDetailsService postDetailsService;

    @Autowired
    public VoteListener(PostDetailsService postDetailsService) {
        this.postDetailsService = postDetailsService;
    }

    public void receiveMessage(final VoteMessage message) {
        System.out.println(" [x] Received '" + message.toString() + "'");
    }
}
