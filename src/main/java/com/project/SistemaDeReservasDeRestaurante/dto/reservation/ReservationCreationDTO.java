package com.project.SistemaDeReservasDeRestaurante.dto.reservation;

import java.time.LocalDateTime;

public record ReservationCreationDTO(LocalDateTime bookingDate, Long numberOfGuests) {
  
}
