package com.fullstack.domain.validator;

import com.fullstack.domain.model.User;
import java.util.function.Predicate;

public interface UserValidator extends Validation<User> {

  static UserValidator isEmailValid(String email) {
    return holds(user -> user.getEmail().equals(email), "Email is invalid");
  }

  static UserValidator isEmailUsed(Boolean isUsed) {
    return holds(user -> isUsed, "Email already used");
  }

  private static UserValidator holds(Predicate<User> p, String message) {
    return user -> p.negate().test(user) ? ValidationResult.ok() : ValidationResult.fail(message);
  }

}
