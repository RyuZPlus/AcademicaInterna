package com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MaterialUploadDTO {
	private String title;
    private String description;
    private Long subjectId;
    private MultipartFile file;
}
