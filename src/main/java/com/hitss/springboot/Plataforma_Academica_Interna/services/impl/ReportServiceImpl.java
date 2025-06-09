package com.hitss.springboot.Plataforma_Academica_Interna.services.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.Grade;
import com.hitss.springboot.Plataforma_Academica_Interna.repositories.GradeRepository;
import com.hitss.springboot.Plataforma_Academica_Interna.services.ReportService;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
    private GradeRepository gradeRepository;

    @Override
    public List<Map<String, Object>> getAverageGradesPerCourseAndSubject() {
        List<Grade> grades = gradeRepository.findAll();
        return grades.stream()
            .collect(Collectors.groupingBy(
                g -> g.getSubject().getCourse().getName() + "-" + g.getSubject().getName(),
                Collectors.averagingDouble(Grade::getGrade)
            ))
            .entrySet()
            .stream()
            .map(e -> {
                String[] parts = e.getKey().split("-");
                Map<String, Object> result = new HashMap<>();
                result.put("course", parts[0]);
                result.put("subject", parts[1]);
                result.put("average", e.getValue());
                return result;
            })
            .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> getStudentGradeHistory(Long studentId) {
        return gradeRepository.findAll().stream()
            .filter(g -> g.getStudent().getId().equals(studentId))
            .map(g -> {
                Map<String, Object> item = new HashMap<>();
                item.put("subject", g.getSubject().getName());
                item.put("course", g.getSubject().getCourse().getName());
                item.put("period", g.getSchoolPeriod().getName());
                item.put("grade", g.getGrade());
                item.put("observations", g.getObservations());
                return item;
            }).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> getFinalReportByCourse(Long courseId) {
        return gradeRepository.findAll().stream()
            .filter(g -> g.getSubject().getCourse().getId().equals(courseId))
            .collect(Collectors.groupingBy(
                g -> g.getStudent().getId(),
                Collectors.averagingDouble(Grade::getGrade)
            ))
            .entrySet().stream()
            .map(e -> {
                Map<String, Object> result = new HashMap<>();
                result.put("studentId", e.getKey());
                result.put("averageGrade", e.getValue());
                return result;
            }).collect(Collectors.toList());
    }
}
