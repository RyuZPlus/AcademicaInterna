package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Course;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Subject;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Teacher;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.SubjectRequestDTO;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.SubjectResponseDTO;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.SubjectRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.services.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{
	@Autowired
    private SubjectRepository subjectRepository;

	@Override
	public Subject createSubject(SubjectRequestDTO dto) {
	    Teacher teacher = new Teacher();
	    teacher.setId(dto.getTeacherId());

	    Course course = new Course();
	    course.setId(dto.getCourseId());

	    Subject subject = new Subject();
	    subject.setName(dto.getName());
	    subject.setTeacher(teacher);
	    subject.setCourse(course);

	    return subjectRepository.save(subject);
	}

	@Override
	public List<SubjectResponseDTO> getAllSubjects() {
		return subjectRepository.findAll()
	            .stream()
	            .map(this::mapToDTO)
	            .toList();
	}

	@Override
	public SubjectResponseDTO getSubjectById(Long id) {
	    return mapToDTO(
	        subjectRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Asignatura no encontrada con ID: " + id))
	    );
	}

	@Override
	public Subject updateSubject(Long id, Subject subject) {
		Subject existingSubject = getSubjectEntityById(id); 
	    existingSubject.setName(subject.getName());
	    existingSubject.setTeacher(subject.getTeacher());
	    existingSubject.setCourse(subject.getCourse());
	    return subjectRepository.save(existingSubject);
	}

	@Override
	public void deleteSubject(Long id) {
		Subject subject = getSubjectEntityById(id); 
	    subjectRepository.delete(subject);
	}
	
	@Override
	public Subject getSubjectEntityById(Long id) {
	    return subjectRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Asignatura no encontrada con ID: " + id));
	}

	private SubjectResponseDTO mapToDTO(Subject subject) {
	    SubjectResponseDTO dto = new SubjectResponseDTO();
	    dto.setId(subject.getId());
	    dto.setName(subject.getName());

	    if (subject.getTeacher() != null) {
	        dto.setTeacherId(subject.getTeacher().getId());
	        dto.setSpecialty(subject.getTeacher().getSpecialty());

	        if (subject.getTeacher().getUser() != null) {
	            dto.setTeacherName(subject.getTeacher().getUser().getUsername());
	        }
	    }

	    if (subject.getCourse() != null) {
	        dto.setCourseId(subject.getCourse().getId());
	        dto.setCourseName(subject.getCourse().getName());
	        dto.setAcademicYear(subject.getCourse().getAcademicYear());
	    }

	    return dto;
	}
}
