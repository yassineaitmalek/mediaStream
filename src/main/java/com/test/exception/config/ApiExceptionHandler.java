package com.test.exception.config;

import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.controllers.config.AbstractController;
import com.test.controllers.config.ApiExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler implements AbstractController {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiExceptionResponse> handleExceptions(Exception e) {
    log.info("Exception class {}", e.getClass());
    log.error(e.getMessage(), e);
    return internalException(e);
  }

  @ExceptionHandler(value = { ApiException.class })
  public ResponseEntity<ApiExceptionResponse> handleApiException(ApiException e) {
    if (e.getCause() != null) {
      log.error(e.getMessage(), e);
    } else {
      log.error(e.getMessage());
    }
    return badRequest(e);

  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiExceptionResponse> handleExceptions(ResourceNotFoundException e) {

    log.error(e.getMessage());
    return notFound(e);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiExceptionResponse> handleValidationExceptions(ConstraintViolationException e) {
    log.error(e.getMessage());
    return badRequest(e);

  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
    log.error(e.getMessage());
    return badRequest(e);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<ApiExceptionResponse> handleValidationExceptions(BindException e) {

    log.error(e.getMessage());
    return badRequest(e);
  }

}
