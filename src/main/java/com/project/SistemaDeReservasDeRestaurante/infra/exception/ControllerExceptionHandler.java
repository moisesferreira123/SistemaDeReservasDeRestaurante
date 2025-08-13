package com.project.SistemaDeReservasDeRestaurante.infra.exception;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.SistemaDeReservasDeRestaurante.dto.ExceptionDTO;

@RestControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity duplicateEntry(DataIntegrityViolationException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO("Existing user");
    return ResponseEntity.badRequest().body(exceptionDTO);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity validateDTO(MethodArgumentNotValidException exception) {
    String firstErrorMessage = Optional.ofNullable(exception.getBindingResult().getFieldError())
                                       .map(FieldError::getDefaultMessage)
                                       .orElse("Erro de validação desconhecido");
    ExceptionDTO exceptionDTO = new ExceptionDTO(firstErrorMessage);
    return ResponseEntity.badRequest().body(exceptionDTO);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity generalException(RuntimeException exception) {
    ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage());
    return ResponseEntity.internalServerError().body(exceptionDTO);
  }
}
