package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Grade;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.keys.GradeId;

public interface GradeService {
	Grade saveGrade(Grade grade);
    List<Grade> getGradesBySubject(Long subjectId);
    List<Grade> getGradesByStudent(Long studentId);
    Grade updateGrade(GradeId id, Grade grade);
    void deleteGrade(GradeId id);
}
