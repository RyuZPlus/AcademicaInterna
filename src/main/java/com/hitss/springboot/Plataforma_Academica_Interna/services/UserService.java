package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.User;

public interface UserService {
	List<User> findAll();
	
	User findByUsername(String username);
	
	User save(User user);
	
	User findById(Long id);
	
	User update(User user);
	
	void deleteById(Long id);
}
