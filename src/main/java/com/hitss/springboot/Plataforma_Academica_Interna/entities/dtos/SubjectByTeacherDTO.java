package com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectByTeacherDTO {
	private Long id;
    private String name;
    private String courseName;
    private String academicYear;
}
