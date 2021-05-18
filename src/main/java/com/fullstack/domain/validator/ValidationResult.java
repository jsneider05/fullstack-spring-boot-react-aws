package com.fullstack.domain.validator;

import java.util.function.Supplier;

public class ValidationResult {

  private final boolean valid;
  private final String message;

  public static ValidationResult ok() {
    return new ValidationResult(true, null);
  }

  public static ValidationResult fail(String message) {
    return new ValidationResult(false, message);
  }

  private ValidationResult(boolean valid, String message) {
    this.valid = valid;
    this.message = message;
  }

  public boolean isValid() {
    return valid;
  }

  public void throwIfInvalid() {
    if (!isValid()) {
      throw new IllegalArgumentException(getMessage());
    }
  }

  public void throwIfInvalid(String fieldName) {
    if (!isValid()) {
      throw new IllegalArgumentException(fieldName + ": " + getMessage());
    }
  }

  public <X extends Throwable> void throwIfInvalid(Supplier<? extends X> exceptionSupplier)
      throws X {
    if (!isValid()) {
      throw exceptionSupplier.get();
    }
  }

  public String getMessage() {
    return message;
  }
}
