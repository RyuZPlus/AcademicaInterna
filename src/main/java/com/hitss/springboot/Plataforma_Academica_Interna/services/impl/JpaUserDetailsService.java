package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.User;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByUsername(username);
		
		User user = userOptional.orElseThrow(() ->
	    new UsernameNotFoundException(String.format("Username %s no existe en el sistema!", username))
	);
		
		List<GrantedAuthority> authorities = List.of(
		        new SimpleGrantedAuthority(user.getRole().getName())
		    );
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(), 
				true,
				true,
				true,
				true,
				authorities);
	}
	
}
