package com.appservice.main.service;

import com.appservice.main.entity.LoginEntity;

public interface MysqlService {

	String getPersona();

	boolean insertLogin(LoginEntity persona);

}
