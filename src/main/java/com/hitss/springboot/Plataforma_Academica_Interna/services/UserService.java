package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.User;

public interface UserService {
	List<User> findAll();
	
	User save(User user);
}
