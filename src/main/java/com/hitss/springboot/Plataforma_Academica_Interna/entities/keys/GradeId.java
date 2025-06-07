package com.hitss.springboot.Plataforma_Academica_Interna.entities.keys;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GradeId implements Serializable{
	private Long student;
    private Long subject;
    private Long schoolPeriod;

}
