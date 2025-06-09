package com.hitss.springboot.Plataforma_Academica_Interna.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	Optional<Teacher> findByUserUsername(String username);
}
