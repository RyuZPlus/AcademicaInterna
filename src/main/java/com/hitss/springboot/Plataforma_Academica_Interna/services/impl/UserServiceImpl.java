package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Role;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.User;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.RoleRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.UserRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		List<Role> roles = new ArrayList<>();
		for (Role incomingRole : user.getRole()) {
	        roleRepository.findByName(incomingRole.getName()).ifPresent(roles::add);
	    }
		user.setRole(roles);
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
