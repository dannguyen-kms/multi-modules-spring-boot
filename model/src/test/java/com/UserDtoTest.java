package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDtoTest {
  private static Validator validator;

  @BeforeEach
  void setUp() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  private UserDto buildDto() {
    return UserDto.builder()
        .email("hieudankaz@gmail.com")
        .dob("26/06/2001")
        .name("KMS_hieudan")
        .password("123456789")
        .role("ADMIN")
        .build();
  }



  @Test
  void GiveUserDtoWithNameIsNotValid_thenShouldGiveConstraintViolations() {
    UserDto userDto = buildDto();
    userDto.setName("hieudan");

    Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

    assertThat(violations.size()).isEqualTo(1);
    violations.forEach(
        action -> {
          assertThat(action.getMessage()).isEqualTo("Fields values start don't match!");
          assertThat(action.getPropertyPath().toString()).isEqualTo("name");
        });
  }

  @Test
  void GiveUserDtoWithBirthDateIsNotValid_thenShouldGiveConstraintViolations() {
    UserDto userDto = buildDto();
    userDto.setDob("26/06/2050");

    Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

    assertThat(violations.size()).isEqualTo(1);
    violations.forEach(
        action -> {
          assertThat(action.getMessage()).isEqualTo("Birth date is not valid!");
          assertThat(action.getPropertyPath().toString()).isEqualTo("dob");
        });
  }


  @Test
  void GiveUserDtoValid_thenShouldGiveNOConstraintViolations() {
    UserDto userDto = buildDto();
    Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

    assertThat(violations.size()).isEqualTo(0);
  }
}
