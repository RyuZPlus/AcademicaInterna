package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.SchoolPeriod;

public interface SchoolPeriodService {
	SchoolPeriod createPeriod(SchoolPeriod period);
    List<SchoolPeriod> getAllPeriods();
    SchoolPeriod getPeriodById(Long id);
}
