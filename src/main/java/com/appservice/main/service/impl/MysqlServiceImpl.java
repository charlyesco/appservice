package com.appservice.main.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appservice.main.entity.LoginEntity;
import com.appservice.main.entity.PersonaEntity;
import com.appservice.main.repository.LoginRepository;
import com.appservice.main.repository.PersonasRepository;
import com.appservice.main.service.MysqlService;

@Service
public class MysqlServiceImpl implements MysqlService {

	@Autowired
	private PersonasRepository personasRepository;
	
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public String getPersona() {
		List<PersonaEntity> list = null;
		try {
			list = personasRepository.findAll();
		} catch (Exception e) {
			return "Error: " + e;
		}
		return "OK: " + list.get(0).toString();
	}
	
	@Override
	public boolean insertLogin(LoginEntity persona) {
		try {
			loginRepository.save(persona);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
