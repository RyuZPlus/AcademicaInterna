package com.hitss.springboot.Plataforma_Academica_Interna.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.User;
import com.hitss.springboot.Plataforma_Academica_Interna.services.ReportService;
import com.hitss.springboot.Plataforma_Academica_Interna.services.StudentService;
import com.hitss.springboot.Plataforma_Academica_Interna.services.UserService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
	@Autowired
    private ReportService reportService;
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;

    @GetMapping("/grades-average")
    public List<Map<String, Object>> getPromediosPorCursoYAsignatura() {
        return reportService.getAverageGradesPerCourseAndSubject();
    }

    @GetMapping("/student-history/{id}")
    public List<Map<String, Object>> getHistorialNotas(@PathVariable Long id) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        User currentUser = userService.findByUsername(username);
        String userRole = currentUser.getRole().getName();
        
        if (userRole.equalsIgnoreCase("ROLE_STUDENT")) {
            Long currentStudentId = studentService.getStudentIdByUserId(currentUser.getId());
            if (!id.equals(currentStudentId)) {
                throw new AccessDeniedException("No puedes ver el historial de otro estudiante.");
            }
        }
        
        return reportService.getStudentGradeHistory(id);
    }

    @GetMapping("/final-report/{id}")
    public List<Map<String, Object>> getReporteFinalPorCurso(@PathVariable Long id) {
        return reportService.getFinalReportByCourse(id);
    }
}
