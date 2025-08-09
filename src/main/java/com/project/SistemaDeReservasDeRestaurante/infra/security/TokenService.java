package com.project.SistemaDeReservasDeRestaurante.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.SistemaDeReservasDeRestaurante.domain.user.User;

@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  private String secretKey;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secretKey); 
      String token = JWT.create()
              .withIssuer("login-auth-api")
              .withSubject(user.getEmail())
              .withExpiresAt(generateExpirationDate())
              .sign(algorithm);
      return token;
    } catch(JWTCreationException exception) {
      throw new RuntimeException("Erro ao registrar o usuário");
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secretKey); 
      return JWT.require(algorithm)
              .withIssuer("login-auth-api")
              .build()
              .verify(token)
              .getSubject();
    } catch(JWTVerificationException exception) {
      return null;
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}

