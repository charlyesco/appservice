package com.appservice.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/controller")
public class MainController {
	
	@Tag(name = "get", description = "GET methods of Employee APIs")
	@GetMapping("/test")
	public String test() {
		return "test success";
	}
	

}
