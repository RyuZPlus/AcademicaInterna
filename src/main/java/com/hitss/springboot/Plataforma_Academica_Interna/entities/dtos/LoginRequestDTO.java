package com.hitss.springboot.Plataforma_Academica_Interna.entities.dtos;

import lombok.Data;

@Data
public class LoginRequestDTO {
	private String username;
    private String password;
}
