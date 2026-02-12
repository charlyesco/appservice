package com.appservice.main.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appservice.main.dao.MongoDao;
import com.appservice.main.entity.LoginEntity;
import com.appservice.main.repository.LoginRepository;
import com.appservice.main.service.MysqlService;
import com.appservice.main.util.Util;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MysqlController", description = "CRUD personas ddbb")
@RestController
@RequestMapping("/mysql")
public class MysqlController {

	@Autowired
	MysqlService mysqlService;

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	private MongoDao mongoDao;

	// sin tag @PreAuthorize el metodo es publico
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/readPersonas")
	public ResponseEntity<String> readPersonas(Principal principal, Authentication authentication, String hola) {
		System.out.print("variable: " + hola);
		LoginEntity user = loginRepository.findUser("user");

		String persona = mongoDao.getFindByIdPersona(1);

		String persona2 = mysqlService.getPersona(1);

		return ResponseEntity.ok("Mongo: " + persona + " Mysql: " + persona2);
	}

	@PostMapping("/insertPersonas")
	public ResponseEntity<String> insertPersonas(String username,
			String pass) {
		LoginEntity login = new LoginEntity();
		login.setUsername(username);
		login.setRoles("USER");
		login.setPassword(Util.encondePass(pass));
		return ResponseEntity.ok("Result: " + mysqlService.insertLogin(login));
	}

	@PostMapping("/updatePersonas")
	public ResponseEntity<String> updatePersonas() {
		return ResponseEntity.ok("Success");
	}

	
	@PostMapping("/deletePersonas")
	public ResponseEntity<String> deletePersonas() {
		return ResponseEntity.ok("Success");
	}

}
