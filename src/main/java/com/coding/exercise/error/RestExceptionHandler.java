package com.coding.exercise.error;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked","rawtypes"})
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    ErrorResponse err =
        new ErrorResponse(
            LocalDateTime.now(), "Constraint Violations", ex.getBindingResult().toString());

    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
  }
}
