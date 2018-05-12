package com.microreddit.app.config;


import com.microreddit.app.amqp.listeners.VoteListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author Josh Harkema
 */
@Configuration
@EnableRabbit
@PropertySource("classpath:environment.properties")
public class AMQPConfig {
    private static String HOST;
    private static String USER;
    private static String PASSWORD;
    private static String TOPIC_EXCHANGE;
    private static String COMMAND_QUEUE;

    @Autowired
    public AMQPConfig(final Environment env) {
        HOST = env.getProperty("rabbit.host");
        USER = env.getProperty("rabbit.user");
        PASSWORD = env.getProperty("rabbit.password");
        TOPIC_EXCHANGE = env.getProperty("rabbit.topic.exchange");
        COMMAND_QUEUE = env.getProperty("rabbit.command.queue");
    }

    @Bean
    public Queue voteQueue() {
        return new Queue(COMMAND_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding commandBinding(TopicExchange exchange) {
        return BindingBuilder.bind(voteQueue()).to(exchange).with("command");
    }

    @Bean
    public SimpleMessageListenerContainer viewListenerContainer(ConnectionFactory connectionFactory,
                                                                MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(COMMAND_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(VoteListener voteListener) {
        return new MessageListenerAdapter(voteListener, "receiveMessage");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(HOST);
        connectionFactory.setUsername(USER);
        connectionFactory.setPassword(PASSWORD);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        final RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

}
