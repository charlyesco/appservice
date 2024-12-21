package com.appservice.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.appservice.main.service.MysqlService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/mysql")
public class MysqlController {

	@Autowired
	MysqlService mysqlService;

	@Tag(name = "get", description = "POST Select personas ddbb")
	@GetMapping("/getPersonas")
	public ResponseEntity<String> getPersonas() {
		return ResponseEntity.ok(mysqlService.getPersona());
	}

	@Tag(name = "post", description = "POST Select personas ddbb")
	@PostMapping("/readPersonas")
	public ResponseEntity<String> readPersonas() {
		return ResponseEntity.ok("Success");
	}

	@Tag(name = "post", description = "POST Select personas ddbb")
	@PostMapping("/updatePersonas")
	public ResponseEntity<String> updatePersonas() {
		return ResponseEntity.ok("Success");
	}

	@Tag(name = "post", description = "POST Select personas ddbb")
	@PostMapping("/deletePersonas")
	public ResponseEntity<String> deletePersonas() {
		return ResponseEntity.ok("Success");
	}

}
