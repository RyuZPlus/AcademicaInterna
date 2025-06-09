package com.hitss.springboot.Plataforma_Academica_Interna.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Grade;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.Student;
import com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos.StudentRequestDTO;
import com.hitss.springboot.Plataforma_Academica_Interna.services.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequestDTO dto) {
        Student createdStudent = studentService.createStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/grades")
    public ResponseEntity<?> getGradesByStudentId(@PathVariable Long id) {
        List<Grade> grades = studentService.getGradesByStudentId(id);
        return ResponseEntity.ok(grades);
    }
}
