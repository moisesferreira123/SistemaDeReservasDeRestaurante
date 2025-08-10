package com.project.SistemaDeReservasDeRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.SistemaDeReservasDeRestaurante.domain.reservation.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  
}
