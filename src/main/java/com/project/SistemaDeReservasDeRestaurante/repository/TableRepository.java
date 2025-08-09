package com.project.SistemaDeReservasDeRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.SistemaDeReservasDeRestaurante.domain.table.Tables;

public interface TableRepository extends JpaRepository<Tables, Long> {
  
}
