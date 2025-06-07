package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Course;

public interface CourseService {
	Course createCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
}
