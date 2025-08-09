package com.project.SistemaDeReservasDeRestaurante.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.SistemaDeReservasDeRestaurante.domain.user.User;
import com.project.SistemaDeReservasDeRestaurante.dto.ErrorResponseDTO;
import com.project.SistemaDeReservasDeRestaurante.dto.user.AuthenticationDTO;
import com.project.SistemaDeReservasDeRestaurante.dto.user.LoginResponseDTO;
import com.project.SistemaDeReservasDeRestaurante.dto.user.RegisterDTO;
import com.project.SistemaDeReservasDeRestaurante.infra.security.TokenService;
import com.project.SistemaDeReservasDeRestaurante.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
    if(userRepository.findByEmail(data.email()) != null) {
      return ResponseEntity.badRequest().body(new ErrorResponseDTO("Email já cadastrado."));
    }
    
    String encrpytedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(data.name(), data.email(), encrpytedPassword);

    userRepository.save(newUser);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    var auth = authenticationManager.authenticate(usernamePassword);
    var token = tokenService.generateToken((User) auth.getPrincipal());
    return ResponseEntity.ok(new LoginResponseDTO(token));
  }
}
