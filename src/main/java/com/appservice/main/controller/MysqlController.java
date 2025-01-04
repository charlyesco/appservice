package com.appservice.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appservice.main.entity.LoginEntity;
import com.appservice.main.entity.PersonaEntity;
import com.appservice.main.service.MysqlService;
import com.appservice.main.util.Util;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/mysql")
public class MysqlController {

	@Autowired
	MysqlService mysqlService;

	@Tag(name = "get", description = "POST Read personas ddbb")
	@GetMapping("/readPersonas")
	public ResponseEntity<String> readPersonas() {
		return ResponseEntity.ok(mysqlService.getPersona());
	}

	@Tag(name = "post", description = "POST Insert personas ddbb")
	@PostMapping("/insertPersonas")
	public ResponseEntity<String> insertPersonas(@RequestParam(name = "username") String username,@RequestParam(name = "pass")String pass ) {
		LoginEntity login = new LoginEntity();
		login.setUsername(username);
		login.setRoles("USER");
		login.setPassword(Util.encondePass(pass));
		return ResponseEntity.ok("Result: "+mysqlService.insertLogin(login));
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
