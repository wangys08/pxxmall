package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value="classpath:spring.xml")
public class StarterWeb {
	
	public static void main(String[] args) {
		SpringApplication.run(StarterWeb.class, args);
	}
}
