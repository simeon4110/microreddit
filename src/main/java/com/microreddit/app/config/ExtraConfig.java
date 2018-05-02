package com.microreddit.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Misc configs.
 *
 * @author Josh Harkema
 */
@Configuration
public class ExtraConfig {
    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol("SMTP");
        javaMailSender.setHost("192.168.0.11");
        javaMailSender.setPort(25);
        return javaMailSender;
    }

}
