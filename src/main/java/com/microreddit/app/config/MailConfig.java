package com.microreddit.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Misc configs.
 *
 * @author Josh Harkema
 */
@Configuration
@PropertySource("classpath:environment.properties")
public class MailConfig {
    private static String PROTOCOL;
    private static String HOST;
    private static int PORT;

    @Autowired
    public MailConfig(final Environment env) {
        PROTOCOL = env.getProperty("mail.protocol");
        HOST = env.getProperty("mail.host");
        PORT = Integer.parseInt(env.getProperty("mail.port"));
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol(PROTOCOL);
        javaMailSender.setHost(HOST);
        javaMailSender.setPort(PORT);
        return javaMailSender;
    }

}
