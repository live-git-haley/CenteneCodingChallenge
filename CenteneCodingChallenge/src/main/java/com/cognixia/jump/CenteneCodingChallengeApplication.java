package com.cognixia.jump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CenteneCodingChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CenteneCodingChallengeApplication.class, args);
	}

}
