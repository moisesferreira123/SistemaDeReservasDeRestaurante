package com.project.SistemaDeReservasDeRestaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.SistemaDeReservasDeRestaurante.domain.user.User;
import com.project.SistemaDeReservasDeRestaurante.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
  }
}
