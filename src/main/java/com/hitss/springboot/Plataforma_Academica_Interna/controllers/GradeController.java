package com.hitss.springboot.Plataforma_Academica_Interna.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Grade;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.keys.GradeId;
import com.hitss.springboot.Plataforma_Academica_Interna.services.GradeService;

@RestController
@RequestMapping("/api/grade")
public class GradeController {
	@Autowired
    private GradeService gradeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.saveGrade(grade));
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<?> getBySubject(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.getGradesBySubject(id));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.getGradesByStudent(id));
    }

    @PutMapping
    public ResponseEntity<Grade> update(@RequestBody Grade grade) {
        GradeId id = new GradeId(
            grade.getStudent().getId(),
            grade.getSubject().getId(),
            grade.getSchoolPeriod().getId()
        );
        return ResponseEntity.ok(gradeService.updateGrade(id, grade));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Grade grade) {
        GradeId id = new GradeId(
            grade.getStudent().getId(),
            grade.getSubject().getId(),
            grade.getSchoolPeriod().getId()
        );
        gradeService.deleteGrade(id);
        return ResponseEntity.ok().build();
    }
}
