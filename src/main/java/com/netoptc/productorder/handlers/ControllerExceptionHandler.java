package com.netoptc.productorder.handlers;


import com.netoptc.productorder.dtos.FieldErrorDto;
import com.netoptc.productorder.dtos.ResponseErrorDto;
import com.netoptc.productorder.exceptions.BadRequestException;
import com.netoptc.productorder.exceptions.ForbiddenException;
import com.netoptc.productorder.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> handlerResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ResponseErrorDto error = new ResponseErrorDto(Instant.now(), request.getRequestURI(), status.value(), e.getMessage() );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseErrorDto> handlerResourceNotFound(BadRequestException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ResponseErrorDto error = new ResponseErrorDto(Instant.now(), request.getRequestURI(), status.value(), e.getMessage() );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDto> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ResponseErrorDto err = new ResponseErrorDto(Instant.now(), request.getRequestURI(), status.value(), "Dados inválidos");
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addFieldError(new FieldErrorDto(f.getField(), f.getDefaultMessage()));
        }
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ResponseErrorDto> forbidden(ForbiddenException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ResponseErrorDto err = new ResponseErrorDto(Instant.now(), request.getRequestURI(), status.value(), e.getMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseErrorDto> forbidden(AccessDeniedException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ResponseErrorDto err = new ResponseErrorDto(Instant.now(), request.getRequestURI(), status.value(), "Acesso negado");
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDto> handleGenericException(Exception e, HttpServletRequest request) {
        logger.error("An error occurred", e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseErrorDto err = new ResponseErrorDto(Instant.now(), request.getRequestURI(), status.value(), "Ocorreu um erro interno");
        return ResponseEntity.status(status).body(err);
    }


}
