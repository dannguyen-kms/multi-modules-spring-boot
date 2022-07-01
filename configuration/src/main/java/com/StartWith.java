package com;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = StartWithValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
public @interface StartWith {
  String message() default "Fields values start don't match!";

  String value();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
