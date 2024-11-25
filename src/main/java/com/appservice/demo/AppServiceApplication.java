package com.appservice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AppServiceApplication {

	private String name;

	public static void main(String[] args) {
		SpringApplication.run(AppServiceApplication.class, args);
	}
	
	 @GetMapping("/hello")
	    public String hello() {
		return String.format("Hello %s!", name);
	    }

}
