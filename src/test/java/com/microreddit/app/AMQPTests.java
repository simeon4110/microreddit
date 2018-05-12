package com.microreddit.app;

import com.microreddit.app.amqp.receivers.CommentReceiverImpl;
import com.microreddit.app.amqp.receivers.KarmaReceiverImpl;
import com.microreddit.app.amqp.receivers.PostReceiverImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Josh Harkema
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AMQPTests {
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private KarmaReceiverImpl karmaQueue;
    @Autowired
    private CommentReceiverImpl commentQueue;
    @Autowired
    private PostReceiverImpl postQueue;

    @Test
    public void TestKarmaReceiver() {
        String message = "hello karma!";
        template.convertAndSend(karmaQueue.getName(), message);
        System.out.println(" [karma] Sent '" + message + "'");
    }

    @Test
    public void TestCommentReceiver() {
        String message = "hello comments!";
        template.convertAndSend(commentQueue.getName(), message);
        System.out.println(" [comment] Sent '" + message + "'");
    }

    @Test
    public void TextPostReceiver() {
        String message = "hello posts!";
        template.convertAndSend(postQueue.getName(), message);
        System.out.println(" [post] Sent '" + message + "'");
    }
}
