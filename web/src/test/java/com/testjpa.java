package com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class testjpa {
  @Test
  void findByEmail() {
    String email = "hieudan@gmail.com";
    User user = User.builder().email(email).name("hieudan").dob(LocalDate.now()).build();

    //    userRepository.findByEmail(email);
  }
}
