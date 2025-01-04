package com.appservice.main.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.appservice.main.util.Util;

@Component
public class AuthenticationProviderProject implements AuthenticationProvider {

	    @Autowired
	    private UserDetailsService userDetailsService;

	    @Override
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	        try{
	            UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
	            System.out.println("User Roles: " + userDetails.getAuthorities());
	            if (authentication.getCredentials().toString().equals(userDetails.getPassword())) {
	                // Si las contraseñas coinciden, autenticamos al usuario
	                return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), authentication.getCredentials(), userDetails.getAuthorities());
	            } else {
	                // Si las contraseñas no coinciden, lanzamos una excepción de credenciales incorrectas
	                throw new BadCredentialsException("Invalid Credentials");
	            }
	            
	        }catch (UsernameNotFoundException e){
	            throw new BadCredentialsException("Invalid Credentials");
	        }
	    }

	    @Override
	    public boolean supports(Class<?> authentication) {
	        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	    }
	}
	
