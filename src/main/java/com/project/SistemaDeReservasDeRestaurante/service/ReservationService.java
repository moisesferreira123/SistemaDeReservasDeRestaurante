package com.project.SistemaDeReservasDeRestaurante.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SistemaDeReservasDeRestaurante.domain.reservation.Reservation;
import com.project.SistemaDeReservasDeRestaurante.domain.reservation.ReservationStatus;
import com.project.SistemaDeReservasDeRestaurante.domain.table.TableStatus;
import com.project.SistemaDeReservasDeRestaurante.domain.table.Tables;
import com.project.SistemaDeReservasDeRestaurante.domain.user.User;
import com.project.SistemaDeReservasDeRestaurante.dto.reservation.ReservationCreationDTO;
import com.project.SistemaDeReservasDeRestaurante.repository.ReservationRepository;

import jakarta.validation.Valid;

@Service
public class ReservationService {
  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private UserService userService;
  
  @Autowired
  private TableService tableService;

  public List<Reservation> listReservations() {
    return reservationRepository.findAll();
  }

  public void createReservation(@Valid ReservationCreationDTO reservationCreationDTO) {
    User user = userService.getUserById(reservationCreationDTO.userId());
    Tables table = tableService.getTableById(reservationCreationDTO.tableId());

    if(reservationCreationDTO.numberOfGuests() > table.getCapacity()) {
      throw new RuntimeException("A mesa escolhida não tem capacidade para " + reservationCreationDTO.numberOfGuests() +
        " pessoas.");
    }

    if(table.getStatus() == TableStatus.INACTIVE) {
      throw new RuntimeException("Mesa escolhida está em manutenção.");
    }

    LocalTime openingHours = LocalTime.of(11, 0, 0);
    LocalTime closingTime = LocalTime.of(23, 0, 0);
    LocalDateTime bookingDate = reservationCreationDTO.bookingDate();

    if(bookingDate.toLocalTime().isBefore(openingHours) ||
       bookingDate.toLocalTime().isAfter(closingTime)) {
        throw new RuntimeException("O restaurante está fechado no horário escolhido");
    }
    
    if(reservationCreationDTO.bookingDate().isBefore(LocalDateTime.now())) {
      throw new RuntimeException("Horário de reserva indispinóvel, pois a data informada já passou.");
    }

    if(table.getStatus() != TableStatus.AVAILABLE) {
      throw new RuntimeException("Mesa indisponível para se fazer a reserva.");
    } 

    Reservation newReservation = new Reservation();
    
    newReservation.setBookingDate(bookingDate);
    newReservation.setNumberOfGuests(reservationCreationDTO.numberOfGuests());
    newReservation.setStatus(ReservationStatus.ACTIVE);
    newReservation.setUser(user);
    newReservation.setTable(table);
    
    reservationRepository.save(newReservation);
  }

  public void cancelReservation(Long reservationId) {
    Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
    if(reservation.getStatus() != ReservationStatus.ACTIVE) {
      throw new RuntimeException("Reserva não pode ser cancelada, pois já está inativa");
    }
    Tables table = tableService.getTableById(reservation.getTable().getId());
    reservation.setStatus(ReservationStatus.CANCELED);
    table.setStatus(TableStatus.AVAILABLE);
    reservationRepository.save(reservation);
    tableService.updateTable(table);
  }
}
