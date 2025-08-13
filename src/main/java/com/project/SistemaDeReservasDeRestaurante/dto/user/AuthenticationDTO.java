package com.project.SistemaDeReservasDeRestaurante.dto.user;

import jakarta.validation.constraints.Email;

public record AuthenticationDTO(@Email(message = "Formato de e-mail inválido") String email, String password) {
  
}
