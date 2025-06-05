package com.hitss.springboot.Plataforma_Academica_Interna.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Teacher;
import com.hitss.springboot.Plataforma_Academica_Interna.services.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	@Autowired
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        Teacher created = teacherService.createTeacher(teacher);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<?> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @GetMapping("/{id}/asignaturas")
    public ResponseEntity<?> getSubjectsByTeacherId(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getSubjectsByTeacherId(id));
    }
}
