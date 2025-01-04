package com.appservice.main.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Util {
	
	public static String encondePass(String pass) {
		return new BCryptPasswordEncoder().encode(pass);
	}
	
}
