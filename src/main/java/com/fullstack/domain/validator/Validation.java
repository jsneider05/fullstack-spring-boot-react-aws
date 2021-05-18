package com.fullstack.domain.validator;

@FunctionalInterface
public interface Validation<K> {

  ValidationResult validate(K param);

  default Validation<K> and(Validation<K> other) {
    return (param) -> {
      ValidationResult firstResult = this.validate(param);
      return !firstResult.isValid() ? firstResult : other.validate(param);
    };
  }

  default Validation<K> or(Validation<K> other) {
    return (param) -> {
      ValidationResult firstResult = this.validate(param);
      return firstResult.isValid() ? firstResult : other.validate(param);
    };
  }

}
