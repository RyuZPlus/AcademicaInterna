package com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos;

import lombok.Data;

@Data
public class SubjectResponseDTO {
	private Long id;
    private String name;

    private Long teacherId;
    private String teacherName;
    private String specialty;

    private Long courseId;
    private String courseName;
    private String academicYear;
}
