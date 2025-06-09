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
					.requestMatchers(HttpMethod.GET, "/api/users", "/api/teachers", "/api/students", "/api/users/me").permitAll()
					.requestMatchers(HttpMethod.GET, "/api/teachers/{id}/subjects",
							"/api/subjects", 
							"/api/subjects/{id}", 
							"/api/period",
							"/api/reports/grades-average",
							"/api/reports/final-report/{id}").hasAnyRole("ADMIN","TEACHER")
					.requestMatchers(HttpMethod.GET, "/api/students/{id}/grades",
							"/api/courses", 
							"/api/courses/{id}", 
							"/api/period/{id}", 
							"/api/grade/subject/{id}",
							"/api/grade/student/{id}",
							"/api/reports/student-history/{id}").hasAnyRole("ADMIN","STUDENT")
					.requestMatchers(HttpMethod.GET, "/api/materials/subject/{id}").hasRole("STUDENT")
					.requestMatchers(HttpMethod.GET, "/api/users/{id}", "/api/teachers/{id}", "/api/students/{id}").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/api/users/register", 
							"/api/teachers", 
							"/api/students", 
							"/api/subjects", 
							"/api/courses", 
							"/api/period").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/api/grade", "/api/materials").hasRole("TEACHER")
					.requestMatchers(HttpMethod.PUT, "/api/users/{id}").hasRole("ADMIN")
					.requestMatchers(HttpMethod.PUT, "/api/subjects/{id}", "/api/grade").hasRole("TEACHER")
					.requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasRole("ADMIN")
					.requestMatchers(HttpMethod.DELETE, "/api/subjects/{id}", "/api/grade", "/api/materials/{id}").hasRole("TEACHER")
						.anyRequest().authenticated())
					.addFilter(new JwtAuthenticationFilter(authenticationManager()))
					.addFilter(new JwtValidationFilter(authenticationManager()))
					.csrf(c -> c.disable())
					.sessionManagement(management -> management
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.build();
	}
}
