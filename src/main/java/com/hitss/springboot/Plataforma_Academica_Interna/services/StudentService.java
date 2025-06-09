package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Grade;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Student;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.StudentRequestDTO;

public interface StudentService {
	Student createStudent(StudentRequestDTO student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    List<Grade> getGradesByStudentId(Long id);
    Long getStudentIdByUserId(Long userId);
}
