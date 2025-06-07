package com.hitss.springboot.Plataforma_Academica_Interna.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
