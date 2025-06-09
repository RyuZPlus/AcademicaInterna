package com.hitss.springboot.Plataforma_Academica_Interna.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Material;

public interface MaterialRepository extends JpaRepository<Material, Long>{
	List<Material> findBySubjectId(Long subjectId);
}
