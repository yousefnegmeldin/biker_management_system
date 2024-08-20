package com.brightskies.biker_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BikerSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikerSystemApplication.class, args);
	}

}
