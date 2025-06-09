package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Material;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Subject;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Teacher;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.MaterialRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.SubjectRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.TeacherRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.services.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {
	private static final String UPLOAD_DIR = "uploads/";
	
	@Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Material uploadMaterial(String title, String description, Long subjectId, MultipartFile file) {
        try {
            if (file.isEmpty()) throw new RuntimeException("Archivo vacío.");

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }

            Teacher teacher = teacherRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

            if (!subject.getTeacher().getId().equals(teacher.getId())) {
                throw new RuntimeException("No puedes subir materiales para esta asignatura.");
            }

            Material material = new Material();
            material.setTitle(title);
            material.setDescription(description);
            material.setUrlFile(path.toString());
            material.setSubject(subject);
            material.setTeacher(teacher);

            return materialRepository.save(material);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar archivo", e);
        }
    }



	@Override
	public List<Material> getMaterialsBySubjectId(Long subjectId) {
		return materialRepository.findBySubjectId(subjectId);
	}

	@Override
	public void deleteMaterial(Long id) {
		materialRepository.deleteById(id);
	}
	

}
