package com.account.accountvalidationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class AccountValidationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountValidationServiceApplication.class, args);
	}

}
