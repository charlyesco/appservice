package com.appservice.main.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class ProjectSecurity {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf
		        .ignoringRequestMatchers("/appservice/login"))
				.authorizeHttpRequests((auth) -> auth
			       .requestMatchers("/mysql/**")
			       // POR DEFECTO SPRING ANADE ROLE
                   .hasRole("USER")
                   .requestMatchers("/appservice/login", "/error").permitAll()
                   .anyRequest().authenticated())
				.formLogin(form -> form
		                .loginPage("/login")  // Página de login personalizada si es necesario
		                .defaultSuccessUrl("/home", true)  // Redirige a "/home" después del login exitoso
		                .permitAll())
				   .httpBasic(Customizer.withDefaults())
				   .exceptionHandling(exception -> 
                   exception.accessDeniedPage("/error"))  // Página de error personalizada
				   .build();

	}

    @Bean
    PasswordEncoder passwordEncoder() {
    	 return  new BCryptPasswordEncoder();
    }
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
	
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;

    }
}
