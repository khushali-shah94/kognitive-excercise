package com.coding.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DataNotValidException extends RuntimeException {


  public DataNotValidException(String message) {
    super(message);
  }
}
