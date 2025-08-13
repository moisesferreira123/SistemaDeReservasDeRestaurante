package com.project.SistemaDeReservasDeRestaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SistemaDeReservasDeRestaurante.domain.table.Tables;
import com.project.SistemaDeReservasDeRestaurante.dto.table.TableCreationDTO;
import com.project.SistemaDeReservasDeRestaurante.dto.table.TableUpdateDTO;
import com.project.SistemaDeReservasDeRestaurante.repository.TableRepository;

import jakarta.validation.Valid;

@Service
public class TableService {
  @Autowired
  private TableRepository tableRepository;

  public List<Tables> listTables() {
    return tableRepository.findAll();
  }

  public void createTable(@Valid TableCreationDTO tableDTO) {
    Tables newTable = new Tables(tableDTO.name(), tableDTO.capacity());
    tableRepository.save(newTable);
  }

  public void updateTable(@Valid TableUpdateDTO tableUpdateDTO, Long tableId) {
    Tables table = tableRepository.findById(tableId).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

    table.setName(tableUpdateDTO.name());
    table.setCapacity(tableUpdateDTO.capacity());
    table.setStatus(tableUpdateDTO.status());

    tableRepository.save(table);
  }

  public void updateTable(Tables table) {
    tableRepository.save(table);
  }

  public void deleteTable(Long tableId) {
    Tables table = tableRepository.findById(tableId).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
    tableRepository.delete(table);
  }

  public Tables getTableById(Long id) {
    return tableRepository.findById(id).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
  }
}
