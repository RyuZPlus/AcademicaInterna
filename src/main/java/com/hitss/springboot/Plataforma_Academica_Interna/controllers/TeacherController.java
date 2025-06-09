package com.hitss.springboot.Plataforma_Academica_Interna.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Teacher;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.TeacherRequestDTO;
import com.hitss.springboot.Plataforma_Academica_Interna.services.TeacherService;
import com.hitss.springboot.Plataforma_Academica_Interna.services.UserService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	@Autowired
    private TeacherService teacherService;
	@Autowired
	private UserService userService;

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody TeacherRequestDTO teacher) {
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

    @GetMapping("/{id}/subjects")
    public ResponseEntity<?> getSubjectsByTeacherId(@PathVariable Long id) {
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long currentUserId = userService.findByUsername(username).getId();

        return ResponseEntity.ok(teacherService.getSubjectsByTeacherId(id, currentUserId));
    }
}
