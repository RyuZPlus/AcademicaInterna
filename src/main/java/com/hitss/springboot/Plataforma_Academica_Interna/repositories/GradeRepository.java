package com.hitss.springboot.Plataforma_Academica_Interna.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Grade;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Student;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.keys.GradeId;

public interface GradeRepository extends JpaRepository<Grade, GradeId>{
	List<Grade> findByStudentId(Student student);
	List<Grade> findBySubjectId(Long subjectId);
}
