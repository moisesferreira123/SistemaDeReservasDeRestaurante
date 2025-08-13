package com.project.SistemaDeReservasDeRestaurante.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
  @NotBlank(message = "Nome não pode ser vazio")
  String name, 

  @NotBlank(message = "Email não pode ser vazio")
  @Email(message = "Formato de e-mail inválido") 
  String email, 

  @NotBlank(message = "Senha não pode ser vazia")
  String password
) {
  
}
