package com.hitss.springboot.Plataforma_Academica_Interna.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.hitss.springboot.Plataforma_Academica_Interna.security.filter.JwtAuthenticationFilter;
import com.hitss.springboot.Plataforma_Academica_Interna.security.filter.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(
				auth -> auth
				.requestMatchers(
						"/v3/api-docs/**",
						"/swagger-ui/**",
						"/swagger-ui.html",
						"/swagger-resources/**",
						"/webjars/**"
					).permitAll()
				.requestMatchers(HttpMethod.GET, "/api/users").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/users").permitAll()
						.anyRequest().authenticated())
			.csrf(c -> c.disable())
			.sessionManagement(management -> management
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.build();
	}
}
