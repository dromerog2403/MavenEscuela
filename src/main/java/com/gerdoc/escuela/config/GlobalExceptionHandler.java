package com.gerdoc.escuela.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex)
    {
        Map<String, Object> response = Map.of(
                "status", HttpStatus.CONFLICT.value(),
                "error", "Conflict",
                "message", "No se puede completar la operación porque existen registros relacionados."
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
}