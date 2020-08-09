package com.snyrise.eli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class OperationNotAllowedException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public OperationNotAllowedException(String message) {
    super(message);
  }

}
