package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Subject;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Teacher;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.TeacherRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.services.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
    private TeacherRepository teacherRepository;

	@Override
	public Teacher createTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher getTeacherById(Long id) {
		return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + id));
	}

	@Override
	public List<Subject> getSubjectsByTeacherId(Long id) {
		Teacher teacher = getTeacherById(id);
        return teacher.getSubjects(); 
	}

}
