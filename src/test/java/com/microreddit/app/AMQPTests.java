package com.microreddit.app;

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

    @Test
    public void TestKarmaReceiver() {
        String message = "hello receiver!";
        template.convertAndSend("ureddit", "command", message);
        System.out.println(" [karma] Sent '" + message + "'");
    }

}
