package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Subject;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.SubjectRequestDTO;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.SubjectResponseDTO;

public interface SubjectService {
	Subject createSubject(SubjectRequestDTO dto);
    List<SubjectResponseDTO> getAllSubjects();
    SubjectResponseDTO getSubjectById(Long id);
    Subject updateSubject(Long id, Subject subject);
    void deleteSubject(Long id);
    Subject getSubjectEntityById(Long id);
}
