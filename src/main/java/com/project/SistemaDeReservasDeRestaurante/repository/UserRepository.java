package com.project.SistemaDeReservasDeRestaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.SistemaDeReservasDeRestaurante.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
  UserDetails findByEmail(String email);
}
