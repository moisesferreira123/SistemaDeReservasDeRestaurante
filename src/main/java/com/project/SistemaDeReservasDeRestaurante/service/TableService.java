package com.project.SistemaDeReservasDeRestaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SistemaDeReservasDeRestaurante.domain.table.Tables;
import com.project.SistemaDeReservasDeRestaurante.dto.table.TableCreationDTO;
import com.project.SistemaDeReservasDeRestaurante.dto.table.TableStatusDTO;
import com.project.SistemaDeReservasDeRestaurante.dto.table.TableUpdateDTO;
import com.project.SistemaDeReservasDeRestaurante.repository.TableRepository;

@Service
public class TableService {
  @Autowired
  private TableRepository tableRepository;

  public List<Tables> listTables() {
    return tableRepository.findAll();
  }

  public void createTable(TableCreationDTO tableDTO) {
    if(tableDTO.name() == null || tableDTO.name().trim().isEmpty()) {
      throw new RuntimeException("Nome de mesa inválido");
    }

    if(tableDTO.capacity() == null) {
      throw new RuntimeException("Capacidade inválida");
    }

    Tables newTable = new Tables(tableDTO.name(), tableDTO.capacity());

    tableRepository.save(newTable);
  }

  public void updateTable(TableUpdateDTO tableUpdateDTO, Long tableId) {
    Tables table = tableRepository.findById(tableId).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
    
    if(tableUpdateDTO.name() == null || tableUpdateDTO.name().trim().isEmpty()) {
      throw new RuntimeException("Nome de mesa inválido");
    }

    if(tableUpdateDTO.capacity() == null) {
      throw new RuntimeException("Capacidade inválida");
    }

    table.setName(tableUpdateDTO.name());
    table.setCapacity(tableUpdateDTO.capacity());
    
    tableRepository.save(table);
  }

  public void updateStatus(TableStatusDTO tableStatus, Long tableId) {
    Tables table = tableRepository.findById(tableId).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

    if(tableStatus.status() == null) {
      throw new RuntimeException("Status inválida");
    }

    table.setStatus(tableStatus.status());

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
