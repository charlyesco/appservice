package com.appservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AppServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(AppServiceApplication.class, args);
	}
	
	 @GetMapping("/hello")
	    public String hello() {
		return String.format("Hello");
	    }

}
