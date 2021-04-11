package com.coding.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ImageNotFoundException extends RuntimeException {


  public ImageNotFoundException() {
    super();
  }
}
