package com.appservice.main.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appservice.main.entity.Persona;
import com.appservice.main.repository.PersonasRepository;
import com.appservice.main.service.MysqlService;

@Service
public class MysqlServiceImpl implements MysqlService {

	@Autowired
	private PersonasRepository personasRepository;

	@Override
	public String getPersona() {
		List<Persona> list = null;
		try {
			list = personasRepository.findAll();
		} catch (Exception e) {
			return "Error: " + e;
		}
		return "OK: " + list.get(0).toString();
	}

}
