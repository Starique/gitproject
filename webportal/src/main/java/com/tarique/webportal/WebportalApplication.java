package com.tarique.webportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tarique.webportal.com.tarique.webportal.backend.persistence.repositories")
public class WebportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebportalApplication.class, args);
	}
}
