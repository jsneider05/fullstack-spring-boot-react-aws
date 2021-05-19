package com.fullstack.domain.validator.annotations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValid, String> {

  private static final String ENUM_VALUES_IS_NOT_VALID = "'%s' is not valid. The values valid are: '%s'";
  private static final String DELIMITER = "', '";

  private List<String> acceptedValues;

  private boolean ignoreCase;

  @Override
  public void initialize(EnumValid constraintAnnotation) {
    acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
        .map(Enum::name)
        .collect(Collectors.toList());
    ignoreCase = constraintAnnotation.ignoreCase();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    boolean isValid;

    if (ignoreCase) {
      isValid = acceptedValues.stream().anyMatch(value::equalsIgnoreCase);
    } else {
      isValid = acceptedValues.stream().anyMatch(value::equals);
    }

    if (!isValid) {
      String valoresValidos = String.join(DELIMITER, acceptedValues);
      String mensaje = String.format(ENUM_VALUES_IS_NOT_VALID, value, valoresValidos);
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(mensaje).addConstraintViolation();
    }
    return isValid;
  }
}
