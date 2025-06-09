package com.hitss.springboot.Plataforma_Academica_Interna.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Material;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.MaterialUploadDTO;
import com.hitss.springboot.Plataforma_Academica_Interna.services.MaterialService;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {
	@Autowired
    private MaterialService materialService;

	@PostMapping
	public ResponseEntity<?> uploadMaterial(@ModelAttribute MaterialUploadDTO dto) {
	    Material material = materialService.uploadMaterial(
	        dto.getTitle(),
	        dto.getDescription(),
	        dto.getSubjectId(),
	        dto.getFile()
	    );
	    return ResponseEntity.ok(material);
	}

    @GetMapping("/subject/{id}")
    public ResponseEntity<?> getMaterialsBySubject(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.getMaterialsBySubjectId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }
}
