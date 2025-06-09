package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Teacher;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.SubjectByTeacherDTO;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.TeacherRequestDTO;

public interface TeacherService {
	Teacher createTeacher(TeacherRequestDTO dto);
    List<Teacher> getAllTeachers();
    Teacher getTeacherById(Long id);
    List<SubjectByTeacherDTO> getSubjectsByTeacherId(Long teacherId, Long currentUserId);
}
