package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Grade;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Student;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.GradeRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.StudentRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private GradeRepository gradeRepository;

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
	}

	@Override
	public List<Grade> getGradesByStudentId(Long id) {
		Student student = getStudentById(id);
	    return gradeRepository.findByStudentId(student);
	}

}
