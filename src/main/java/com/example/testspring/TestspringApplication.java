package com.example.testspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.testspring.repository")
@SpringBootApplication(exclude = SqlInitializationAutoConfiguration.class, scanBasePackages = "com.example.testspring")
public class TestspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestspringApplication.class, args);
	}

}
