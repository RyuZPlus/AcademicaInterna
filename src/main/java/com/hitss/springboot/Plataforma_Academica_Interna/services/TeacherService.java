package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Teacher;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.SubjectByTeacherDTO;

public interface TeacherService {
	Teacher createTeacher(Teacher teacher);
    List<Teacher> getAllTeachers();
    Teacher getTeacherById(Long id);
    List<SubjectByTeacherDTO> getSubjectsByTeacherId(Long teacherId, Long currentUserId);
}
