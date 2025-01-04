package com.appservice.main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.appservice.main.entity.LoginEntity;
import com.appservice.main.repository.LoginRepository;
import com.appservice.main.util.Util;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Cargamos el usuarios y vemos si tiene privilegios
		List<LoginEntity> user = loginRepository.findByUsername(username);
		LoginEntity loginEntity = user.get(0);
		loginEntity.setPassword(Util.encondePass(loginEntity.getPassword()));
//		return user.map(UserDetailModel::new).orElseThrow(() -> new UsernameNotFoundException("Invalid Username"));
		return User.withUsername(loginEntity.getUsername()).password(loginEntity.getPassword()).roles(loginEntity.getRoles()).build();
	}
	
}
