package com.project.SistemaDeReservasDeRestaurante.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SistemaDeReservasDeRestaurante.domain.reservation.Reservation;
import com.project.SistemaDeReservasDeRestaurante.domain.table.Tables;
import com.project.SistemaDeReservasDeRestaurante.domain.user.User;
import com.project.SistemaDeReservasDeRestaurante.dto.reservation.ReservationCreationDTO;
import com.project.SistemaDeReservasDeRestaurante.repository.ReservationRepository;

@Service
public class ReservationService {
  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private UserService userService;
  
  @Autowired
  private TableService tableService;

  public void createReservation(ReservationCreationDTO reservationCreationDTO, Long userId, Long tableId) {
    User user = userService.getUserById(userId);
    Tables table = tableService.getTableById(tableId);
    LocalDateTime openingHours = LocalDate.now().atTime(11, 0);
    LocalDateTime closingTime = LocalDate.now().atTime(23, 0);
    
    if(reservationCreationDTO.bookingDate() == null) {
      throw new RuntimeException("Data de reserva inválida");
    }

    if(reservationCreationDTO.numberOfGuests() == null) {
      throw new RuntimeException("Número de pessoas inválido");
    }

    

    // Fazer a consulta para verificar se dá para fazer a reserva
  }

  public List<Reservation> listReservations() {
    return null;
  }

  public void cancelReservation() {

  }
}
