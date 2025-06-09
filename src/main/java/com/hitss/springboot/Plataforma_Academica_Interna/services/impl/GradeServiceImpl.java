package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Grade;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.SchoolPeriod;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Student;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Subject;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.GradeRequestDTO;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.keys.GradeId;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.GradeRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.SchoolPeriodRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.StudentRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.SubjectRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.services.GradeService;

@Service
public class GradeServiceImpl implements GradeService{
	@Autowired
    private GradeRepository gradeRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private SchoolPeriodRepository schoolPeriodRepository;

	@Override
	public Grade saveGrade(GradeRequestDTO dto) {
		Student student = studentRepository.findById(dto.getStudentId())
		        .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

		    Subject subject = subjectRepository.findById(dto.getSubjectId())
		        .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

		    SchoolPeriod schoolPeriod = schoolPeriodRepository.findById(dto.getSchoolPeriodId())
		        .orElseThrow(() -> new RuntimeException("Periodo escolar no encontrado"));

		    Grade grade = new Grade(student, subject, schoolPeriod, dto.getGrade(), dto.getObservations());
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
	public Grade updateGrade(GradeRequestDTO dto) {
		GradeId id = new GradeId(dto.getStudentId(), dto.getSubjectId(), dto.getSchoolPeriodId());

	    Grade existing = gradeRepository.findById(id).orElseThrow(() ->
	        new RuntimeException("Calificación no encontrada."));

	    existing.setGrade(dto.getGrade());
	    existing.setObservations(dto.getObservations());

	    return gradeRepository.save(existing);
	}

	@Override
	public void deleteGrade(GradeId id) {
		gradeRepository.deleteById(id);
	}
}
