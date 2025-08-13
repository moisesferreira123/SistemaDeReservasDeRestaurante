package com.project.SistemaDeReservasDeRestaurante.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.SistemaDeReservasDeRestaurante.dto.reservation.ReservationCreationDTO;
import com.project.SistemaDeReservasDeRestaurante.dto.reservation.ReservationDTO;
import com.project.SistemaDeReservasDeRestaurante.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
  @Autowired
  private ReservationService reservationService;

  @GetMapping
  public List<ReservationDTO> listReservations() {
    return reservationService.listReservations()
                            .stream()
                            .map(ReservationDTO::new)
                            .collect(Collectors.toList());
  }

  @PostMapping
  public void createReservation(@RequestBody @Valid ReservationCreationDTO reservationCreationDTO) {
    reservationService.createReservation(reservationCreationDTO);
  }

  @PatchMapping("/{id}/cancel")
  public void cancelReservation(@PathVariable Long id) {
    reservationService.cancelReservation(id);
  }
}
