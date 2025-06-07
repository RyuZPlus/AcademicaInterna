package com.hitss.springboot.Plataforma_Academica_Interna.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.SchoolPeriod;
import com.hitss.springboot.Plataforma_Academica_Interna.services.SchoolPeriodService;

@RestController
@RequestMapping("/api/period")
public class SchoolPeriodController {
	@Autowired
    private SchoolPeriodService schoolPeriodService;

    @PostMapping
    public ResponseEntity<?> createPeriod(@RequestBody SchoolPeriod period) {
        return ResponseEntity.ok(schoolPeriodService.createPeriod(period));
    }

    @GetMapping
    public ResponseEntity<?> getAllPeriods() {
        return ResponseEntity.ok(schoolPeriodService.getAllPeriods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPeriodById(@PathVariable Long id) {
        return ResponseEntity.ok(schoolPeriodService.getPeriodById(id));
    }
}
