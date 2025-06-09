package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Grade;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.GradeRequestDTO;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.keys.GradeId;

public interface GradeService {
	Grade saveGrade(GradeRequestDTO grade);
    List<Grade> getGradesBySubject(Long subjectId);
    List<Grade> getGradesByStudent(Long studentId);
    Grade updateGrade(GradeRequestDTO dto);
    void deleteGrade(GradeId id);
}
