package com.soccer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SoccerMatchingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoccerMatchingApplication.class, args);
	}

}
