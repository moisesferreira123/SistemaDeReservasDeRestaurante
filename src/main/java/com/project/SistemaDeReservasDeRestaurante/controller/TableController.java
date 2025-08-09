package com.project.SistemaDeReservasDeRestaurante.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.SistemaDeReservasDeRestaurante.dto.table.TableCreationDTO;
import com.project.SistemaDeReservasDeRestaurante.dto.table.TableDTO;
import com.project.SistemaDeReservasDeRestaurante.dto.table.TableUpdateDTO;
import com.project.SistemaDeReservasDeRestaurante.service.TableService;

@RestController
@RequestMapping("/tables")
public class TableController {
  @Autowired
  private TableService tableService;

  @GetMapping
  public List<TableDTO> listTables() {
    return tableService.listTables()
                      .stream()
                      .map(TableDTO::new)
                      .collect(Collectors.toList());
  }

  @PostMapping
  public void createTable(@RequestBody TableCreationDTO tableCreationDTO) {
    tableService.createTable(tableCreationDTO);
  }

  @PutMapping("/{tableId}")
  public void updateTable(@RequestBody TableUpdateDTO tableUpdateDTO, @PathVariable Long tableId) {
    tableService.updateTable(tableUpdateDTO, tableId);
  }

  @DeleteMapping("/{tableId}")
  public void deleteTable(@PathVariable Long tableId) {
    tableService.deleteTable(tableId);
  }
}
