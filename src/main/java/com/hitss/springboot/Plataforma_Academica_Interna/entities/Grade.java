package com.hitss.springboot.Plataforma_Academica_Interna.entities;

import com.hitss.springboot.Plataforma_Academica_Interna.entities.keys.GradeId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grades")
@IdClass(GradeId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
	@Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Id
    @ManyToOne
    @JoinColumn(name = "school_period_id")
    private SchoolPeriod schoolPeriod;

    private Double grade;

    private String observations;
}
