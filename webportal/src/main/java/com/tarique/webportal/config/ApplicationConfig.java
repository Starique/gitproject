package com.tarique.webportal.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Mehnuma on 1/16/2017.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.tarique.webportal.backend.persistence.repositories")
@EntityScan(basePackages = "com.tarique.webportal.backend.persistence.domain.backend")
@EnableTransactionManagement
@PropertySource("file:///${user.home}/.properties/application-common.properties")
public class ApplicationConfig {
}
