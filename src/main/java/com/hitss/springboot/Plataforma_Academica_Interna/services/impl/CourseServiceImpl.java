package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Course;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.CourseRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
    private CourseRepository courseRepository;

	@Override
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(Long id) {
		return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
	}

}
