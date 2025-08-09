package com.project.SistemaDeReservasDeRestaurante.dto.table;

import com.project.SistemaDeReservasDeRestaurante.domain.table.TableStatus;

public record TableUpdateDTO(String name, Long capacity, TableStatus status) {
  
}
