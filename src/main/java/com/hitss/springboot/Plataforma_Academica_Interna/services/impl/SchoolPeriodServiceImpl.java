package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.SchoolPeriod;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.SchoolPeriodRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.services.SchoolPeriodService;

@Service
public class SchoolPeriodServiceImpl implements SchoolPeriodService{
	@Autowired
    private SchoolPeriodRepository repository;

	@Override
	public SchoolPeriod createPeriod(SchoolPeriod period) {
		return repository.save(period);
	}

	@Override
	public List<SchoolPeriod> getAllPeriods() {
		return repository.findAll();
	}

	@Override
	public SchoolPeriod getPeriodById(Long id) {
		return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Periodo escolar no encontrado con ID: " + id));
	}

}
