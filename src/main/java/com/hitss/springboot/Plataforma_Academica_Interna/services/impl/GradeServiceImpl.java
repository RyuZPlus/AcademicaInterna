package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Grade;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Student;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.keys.GradeId;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.GradeRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.StudentRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.services.GradeService;

@Service
public class GradeServiceImpl implements GradeService{
	@Autowired
    private GradeRepository gradeRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Grade saveGrade(Grade grade) {
		return gradeRepository.save(grade);
	}

	@Override
	public List<Grade> getGradesBySubject(Long subjectId) {
		return gradeRepository.findBySubjectId(subjectId);
	}

	@Override
	public List<Grade> getGradesByStudent(Long studentId) {
		Student student = studentRepository.findById(studentId)
		        .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + studentId));
		    return gradeRepository.findByStudentId(student);
	}

	@Override
	public Grade updateGrade(GradeId id, Grade grade) {
		Grade existing = gradeRepository.findById(id).orElseThrow(() ->
        new RuntimeException("Calificación no encontrada."));
	    existing.setGrade(grade.getGrade());
	    existing.setObservations(grade.getObservations());
	    return gradeRepository.save(existing);
	}

	@Override
	public void deleteGrade(GradeId id) {
		gradeRepository.deleteById(id);
	}
}
