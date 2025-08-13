package com.project.SistemaDeReservasDeRestaurante.dto.user;

import jakarta.validation.constraints.Email;

public record RegisterDTO(String name, @Email(message = "Formato de e-mail inválido") String email, String password) {
  
}
