package com.hitss.springboot.Plataforma_Academica_Interna.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	Optional<Student> findByUserId(Long userId);
}
