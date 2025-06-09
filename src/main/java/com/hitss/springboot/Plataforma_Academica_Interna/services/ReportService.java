package com.hitss.springboot.Plataforma_Academica_Interna.services;

import java.util.List;
import java.util.Map;

public interface ReportService {
	List<Map<String, Object>> getAverageGradesPerCourseAndSubject();
    List<Map<String, Object>> getStudentGradeHistory(Long studentId);
    List<Map<String, Object>> getFinalReportByCourse(Long courseId);
}
