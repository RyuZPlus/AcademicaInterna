package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Subject;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.SubjectRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.services.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{
	@Autowired
    private SubjectRepository subjectRepository;

	@Override
	public Subject createSubject(Subject subject) {
		return subjectRepository.save(subject);
	}

	@Override
	public List<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}

	@Override
	public Subject getSubjectById(Long id) {
		return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada con ID: " + id));
	}

	@Override
	public Subject updateSubject(Long id, Subject subject) {
		Subject existingSubject = getSubjectById(id);
        existingSubject.setName(subject.getName());
        existingSubject.setTeacher(subject.getTeacher());
        existingSubject.setCourse(subject.getCourse());
        return subjectRepository.save(existingSubject);
	}

	@Override
	public void deleteSubject(Long id) {
		Subject subject = getSubjectById(id);
        subjectRepository.delete(subject);
	}

}
