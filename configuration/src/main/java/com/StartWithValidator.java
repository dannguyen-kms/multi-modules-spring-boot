package com;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartWithValidator implements ConstraintValidator<StartWith, String> {
  private String startWith;

  @Override
  public void initialize(StartWith constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.startWith = constraintAnnotation.value();
  }

  @Override
  public boolean isValid(
      final String value, final ConstraintValidatorContext constraintValidatorContext) {
    if (value != null) {
      return value.startsWith(startWith);
    } else {
      return startWith == null;
    }
  }
}
