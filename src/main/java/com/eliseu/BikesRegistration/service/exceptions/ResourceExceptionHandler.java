package com.eliseu.BikesRegistration.service.exceptions;

import com.eliseu.BikesRegistration.controller.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Bike não encontrada.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ResourceFoundException.class)
    public ResponseEntity<StandardError> resourceFound(ResourceFoundException e, HttpServletRequest request) {
        String error = "Requisição com parâmetro description inválido! ";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UnnecessaryUpdateException.class)
    public ResponseEntity<String> unnecessaryUpdateException(UnnecessaryUpdateException ex) {
        HttpStatus status = HttpStatus.OK;
        return ResponseEntity.status(status).body(ex.getMessage());
    }

}
