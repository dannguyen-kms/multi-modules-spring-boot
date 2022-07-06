package com;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {
  UserMapper userMapper = UserMapper.INSTANCE;

  @Test
  public void givenUserEntitytoPersonWithExpression_whenMaps_thenCorrect() {
    String email = "hieudan@gmail.com";
    UserDto expectedUserDto =
        UserDto.builder()
            .email(email)
            .name("hieudan")
            .role("ADMIN")
            .password("123456789")
            .dob(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .build();

    User userEntity =
        User.builder()
            .password("123456789")
            .role("ADMIN")
            .email(email)
            .name("hieudan")
            .dob(LocalDate.now())
            .build();

    UserDto actualUserDto = userMapper.toDto(userEntity);
    assertThat(actualUserDto).usingRecursiveComparison().isEqualTo(expectedUserDto);
  }

  @Test
  public void givenUserDtotoUserWithExpression_whenMaps_thenCorrect() {
    String email = "hieudan@gmail.com";
    UserDto userDto =
        UserDto.builder()
            .email(email)
            .name("hieudan")
            .role("ADMIN")
            .password("123456789")
            .dob(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .build();

    User expectedUserEntity =
        User.builder()
            .password("123456789")
            .role("ADMIN")
            .email(email)
            .name("hieudan")
            .dob(LocalDate.now())
            .build();

    User actualUserEntity = userMapper.toEntity(userDto);
    assertThat(actualUserEntity).usingRecursiveComparison().isEqualTo(expectedUserEntity);
  }
}
