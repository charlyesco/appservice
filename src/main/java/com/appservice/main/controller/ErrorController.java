package com.appservice.main.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
	@PreAuthorize("hasAuthority('USER')")
	 @GetMapping("/error")
	    public String home(){
	        return "Hello World!";
	    }

}
