package com.microreddit.app.config;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author Josh Harkema
 */
@Configuration
@PropertySource("classpath:environment.properties")
public class AMQPConfig {
    private static String HOST;
    private static String USER;
    private static String PASSWORD;

    @Autowired
    public AMQPConfig(final Environment env) {
        HOST = env.getProperty("rabbit.host");
        USER = env.getProperty("rabbit.user");
        PASSWORD = env.getProperty("rabbit.password");
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

}
