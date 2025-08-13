package com.project.SistemaDeReservasDeRestaurante.dto.reservation;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record ReservationCreationDTO(
  @NotBlank(message = "Data de reserva inválida")
  LocalDateTime bookingDate, 

  @NotBlank(message = "Número de pessoas inválido")
  Long numberOfGuests, 
  
  @NotBlank(message = "Id do usuário inválido")
  Long userId, 

  @NotBlank(message = "Id da mesa inválido")
  Long tableId
) {
  
}
