package com.microreddit.app.config;

import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Josh Harkema
 */
//@SpringBootApplication
@Configuration
public class JmsConfig {
    private final QueueClient queueClient;
    private final TopicClient topicClient;

    @Autowired
    public JmsConfig(QueueClient queueClient, TopicClient topicClient) {
        this.queueClient = queueClient;
        this.topicClient = topicClient;
    }

    // NOTE: Please be noted that below are the minimum code for demonstrating the usage of autowired clients.
    // For complete documentation of Service Bus, reference https://azure.microsoft.com/en-us/services/service-bus/
    private void sendQueueMessage() throws ServiceBusException, InterruptedException {
        final String messageBody = "queue message";
        System.out.println("Sending message: " + messageBody);
        final Message message = new Message(messageBody.getBytes(StandardCharsets.UTF_8));
        queueClient.send(message);
    }

    private void receiveQueueMessage() throws ServiceBusException, InterruptedException {
        queueClient.registerMessageHandler(new MessageHandler(), new MessageHandlerOptions());

        TimeUnit.SECONDS.sleep(5);
        queueClient.close();
    }

    private void sendTopicMessage() throws ServiceBusException, InterruptedException {
        final String messageBody = "topic message";
        System.out.println("Sending message: " + messageBody);
        final Message message = new Message(messageBody.getBytes(StandardCharsets.UTF_8));
        topicClient.send(message);
        topicClient.close();
    }

    static class MessageHandler implements IMessageHandler {
        public CompletableFuture<Void> onMessageAsync(IMessage message) {
            final String messageString = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received message: " + messageString);
            return CompletableFuture.completedFuture(null);
        }

        public void notifyException(Throwable exception, ExceptionPhase phase) {
            System.out.println(phase + " encountered exception:" + exception.getMessage());
        }
    }
}
