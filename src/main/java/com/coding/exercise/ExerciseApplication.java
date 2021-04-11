package com.coding.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableCaching
@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}

}
