package com.project.SistemaDeReservasDeRestaurante.dto.table;

import com.project.SistemaDeReservasDeRestaurante.domain.table.TableStatus;
import com.project.SistemaDeReservasDeRestaurante.domain.table.Tables;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TableDTO {
  private Long id;
  private String name;
  private Long capacity;
  private TableStatus status;

  public TableDTO(Tables table) {
    this.id = table.getId();
    this.name = table.getName();
    this.capacity = table.getCapacity();
    this.status = table.getStatus();
  }
}
