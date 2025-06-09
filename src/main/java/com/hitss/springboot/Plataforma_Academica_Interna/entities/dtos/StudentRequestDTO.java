package com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos;

import lombok.Data;

@Data
public class StudentRequestDTO {
	private Long userId;
    private String enrollmentCode;
    private String currentCourse;
}
