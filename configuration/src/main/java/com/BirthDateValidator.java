package com;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthDateValidator implements ConstraintValidator<BirtDate, String> {
  @Override
  public void initialize(BirtDate constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    try {
      LocalDate dob = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      if (dob.isBefore(LocalDate.now())) {
        return true;
      } else {
        return false;
      }

    } catch (Exception e) {
      return false;
    }
  }
}
