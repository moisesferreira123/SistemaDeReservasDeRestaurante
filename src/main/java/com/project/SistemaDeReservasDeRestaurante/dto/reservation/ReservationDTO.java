package com.project.SistemaDeReservasDeRestaurante.dto.reservation;

import java.time.LocalDateTime;

import com.project.SistemaDeReservasDeRestaurante.domain.reservation.Reservation;
import com.project.SistemaDeReservasDeRestaurante.domain.reservation.ReservationStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO {
  private Long id;
  private LocalDateTime bookingDate;
  private Long numberOfGuests;
  private ReservationStatus status;

  public ReservationDTO(Reservation reservation) {
    this.id = reservation.getId();
    this.bookingDate = reservation.getBookingDate();
    this.numberOfGuests = reservation.getNumberOfGuests();
    this.status = reservation.getStatus();
  }
}
