package com.hitss.springboot.Plataforma_Academica_Interna.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Material;
import com.hitss.springboot.Plataforma_Academica_Interna.services.MaterialService;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {
	@Autowired
    private MaterialService materialService;

    @PostMapping
    public ResponseEntity<?> uploadMaterial(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("subjectId") Long subjectId,
            @RequestParam("teacherId") Long teacherId,
            @RequestParam("file") MultipartFile file
    ) {
        Material material = materialService.uploadMaterial(title, description, subjectId, teacherId, file);
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
