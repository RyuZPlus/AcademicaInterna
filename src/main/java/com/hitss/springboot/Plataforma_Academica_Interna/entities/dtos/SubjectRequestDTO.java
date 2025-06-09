package com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos;

import lombok.Data;

@Data
public class SubjectRequestDTO {
	private String name;
    private Long teacherId;
    private Long courseId;
}
