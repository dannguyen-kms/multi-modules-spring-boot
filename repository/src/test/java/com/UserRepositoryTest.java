package com;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
class UserRepositoryTest {

  @Autowired UserRepository userRepository;

  @Test
  void GivenEmail_WhenUserExist_ThenReturnUser() {
    String email = "hieudan@gmail.com";
    User expectedUser = User.builder().email(email).name("hieudan").dob(LocalDate.now()).build();
    userRepository.save(expectedUser);

    User actualUser = userRepository.findByEmail(email).get();

    assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser);
  }

  @Test
  void GivenEmail_WhenUserNotExist_ThenReturnOptionalEmpty() {
    String email = "hieudan@gmail.com";
    Optional<User> optionalUser = userRepository.findByEmail(email);
    assertThat(optionalUser.isEmpty()).isTrue();
  }
}
