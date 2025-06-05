package com.hitss.springboot.Plataforma_Academica_Interna.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.User;
import com.hitss.springboot.Plataforma_Academica_Interna.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public List<User> list(){
		return userService.findAll();
	}
	@GetMapping("/me")
	public ResponseEntity<?> getAuthenticatedUser(Authentication authentication) {
	    String username = authentication.getName();

	    User user = userService.findByUsername(username);
	    
	    if (user == null) {
	        return ResponseEntity.notFound().build();
	    }

	    user.setPassword(null);

	    return ResponseEntity.ok(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setPassword(null); // opcional: ocultar contraseña
        return ResponseEntity.ok(user);
    }
	
	@PostMapping("/register")
	public ResponseEntity<?> create(@RequestBody User user) {
	    User savedUser = userService.save(user);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User updatedUser) {
	    User existingUser = userService.findById(id);
	    if (existingUser == null) {
	        return ResponseEntity.notFound().build();
	    }

	    existingUser.setUsername(updatedUser.getUsername());
	    existingUser.setEmail(updatedUser.getEmail());
	    existingUser.setRole(updatedUser.getRole());
	    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

	    User savedUser = userService.update(existingUser);
	    savedUser.setPassword(null);
	    //return ResponseEntity.ok(savedUser);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
	    User existingUser = userService.findById(id);
	    if (existingUser == null) {
	        return ResponseEntity.notFound().build();
	    }

	    userService.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
}
