package com.project.SistemaDeReservasDeRestaurante.dto.table;

import jakarta.validation.constraints.NotBlank;

public record TableCreationDTO(
  @NotBlank(message = "Nome da mesa não pode ser vazio.")
  String name, 

  @NotBlank(message = "Capacidade da mesa não pode ser vazia.")
  Long capacity
) {
  
}
