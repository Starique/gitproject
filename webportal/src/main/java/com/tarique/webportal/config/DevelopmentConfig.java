package com.tarique.webportal.config;

import com.tarique.webportal.backend.service.EmailService;
import com.tarique.webportal.backend.service.MockEmailService;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Mehnuma on 1/2/2017.
 */
@Configuration
@Profile("dev")
@PropertySource("file:///${user.home}/.properties/application-dev.properties")
public class DevelopmentConfig {

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

    @Bean
    public ServletRegistrationBean h2ConsoleServerletRegistration() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
        bean.addUrlMappings("/console/*");
        return bean;
    }
}
