package com.project.SistemaDeReservasDeRestaurante.dto.table;

import com.project.SistemaDeReservasDeRestaurante.domain.table.TableStatus;

import jakarta.validation.constraints.NotBlank;

public record TableUpdateDTO(
  @NotBlank(message = "Nome da mesa não pode ser vazio.")
  String name, 

  @NotBlank(message = "Capacidade da mesa não pode ser vazia.")
  Long capacity, 

  @NotBlank(message = "Status da mesa não pode ser vazia.")
  TableStatus status
) {
  
}
