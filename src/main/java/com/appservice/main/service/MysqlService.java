package com.appservice.main.service;

import com.appservice.main.entity.LoginEntity;

public interface MysqlService {

	String getPersona(Integer id);

	boolean insertLogin(LoginEntity persona);

}
