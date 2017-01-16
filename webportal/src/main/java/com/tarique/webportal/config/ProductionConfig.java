package com.tarique.webportal.config;

import com.tarique.webportal.backend.service.EmailService;
import com.tarique.webportal.backend.service.SMTPEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Mehnuma on 1/2/2017.
 */
@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/.properties/application-pro.properties")
public class ProductionConfig {

    @Bean
    public EmailService emailService(){
        return new SMTPEmailService();
    }
}
