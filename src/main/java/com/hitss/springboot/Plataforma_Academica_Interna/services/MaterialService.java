package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Material;

public interface MaterialService {
	Material uploadMaterial(String title, String description, Long subjectId, MultipartFile file);
    List<Material> getMaterialsBySubjectId(Long subjectId);
    void deleteMaterial(Long id);
}
