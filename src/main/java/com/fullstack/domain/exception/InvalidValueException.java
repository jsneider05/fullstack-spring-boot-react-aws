package com.fullstack.domain.exception;

public class InvalidValueException extends RuntimeException {

  public InvalidValueException(String message) {
    super(message);
  }
}
