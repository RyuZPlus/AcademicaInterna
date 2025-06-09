package com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos;

import lombok.Data;

@Data
public class GradeRequestDTO {
	private Long studentId;
    private Long subjectId;
    private Long schoolPeriodId;
    private Double grade;
    private String observations;
}
