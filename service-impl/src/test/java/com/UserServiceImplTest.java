package com;

import org.aspectj.util.Reflection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  private UserService userService;

  @Mock private UserRepository userRepository;
  @Mock private UserMapper userMapper;

  @BeforeEach
  void setUp() {
    userService = new UserServiceImpl(userRepository, userMapper);
  }

  @Test
  void GivenEmail_WhenUserExist_ThenReturnUser() {
    String email = "hieudan@gmail.com";
    User expectedUser = User.builder().email(email).name("hieudan").dob(LocalDate.now()).build();

    Mockito.when(userRepository.findByEmail(expectedUser.getEmail()))
        .thenReturn(Optional.of(expectedUser));

    User actualUser = userService.findUserByEmail(expectedUser.getEmail());

    assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser);
  }

  @Test
  void GivenEmail_WhenUserNotExist_ThenThrowException() {
    String email = "hieudan@gmail.com";
    Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> userService.findUserByEmail(email))
        .hasMessageContaining(String.format("No such user with %s: %s", "email", email));
  }

  @Test
  void GivenUser_TryInsert_WhenUserEmailNotExisted() {

    String email = "hieudan@gmail.com";
    UserDto userDto =
        UserDto.builder()
            .email(email)
            .name("hieudan")
            .dob(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .build();

    User expectedUser = User.builder().email(email).name("hieudan").dob(LocalDate.now()).build();
    Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

    userService.addNewUser(userDto);
    Mockito.verify(userRepository).save(expectedUser);
  }

  @Test
  void GivenUser_TryInsert_WhenUserEmailExisted_ThenThrowException() {

    String email = "hieudan@gmail.com";
    User existedUser = User.builder().email(email).name("hieudan").dob(LocalDate.now()).build();
    UserDto userDto =
        UserDto.builder()
            .email(email)
            .name("hieudan")
            .dob(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .build();

    Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(existedUser));

    assertThatThrownBy(() -> userService.addNewUser(userDto))
        .hasMessageContaining("Email already taken!");
  }

  @Test
  void findAllUser_WhenUserExist_ThenReturnListUser() {
    List<User> expectedList =
        List.of(
            User.builder().email("1@gmail.com").name("hieudan").dob(LocalDate.now()).build(),
            User.builder().email("2@gmail.com").name("hieudan").dob(LocalDate.now()).build(),
            User.builder().email("3@gmail.com").name("hieudan").dob(LocalDate.now()).build(),
            User.builder().email("4@gmail.com").name("hieudan").dob(LocalDate.now()).build(),
            User.builder().email("5@gmail.com").name("hieudan").dob(LocalDate.now()).build());
    Mockito.when(userRepository.findAll()).thenReturn(expectedList);
    List<User> actualList = userService.getAllUsers();
    assertThat(actualList.equals(expectedList)).isTrue();
  }

  @Test
  void findAllUser_WhenNoUserExist_ThenThrowException() {
    List<User> expectedList = List.of();
    Mockito.when(userRepository.findAll()).thenReturn(expectedList);
    assertThatThrownBy(() -> userService.getAllUsers()).hasMessageContaining("Not found any users");
  }

  @Test
  void GivenUser_TryUpdate_WhenUserEmailNotExisted() {

    String email = "hieudan@gmail.com";
    UserDto userDto =
        UserDto.builder()
            .email(email)
            .name("hieudan")
            .dob(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .build();

    User expectedUser = User.builder().email(email).name("hieudan").dob(LocalDate.now()).build();
    Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(expectedUser));

    userService.updateUser(userDto.getEmail(), userDto);
    Mockito.verify(userRepository).save(expectedUser);
  }

  @Test
  void GivenUser_TryUpdate_WhenUserEmailExisted_ThenThrowException() {

    String email = "hieudan@gmail.com";
    UserDto userDto =
        UserDto.builder()
            .email(email)
            .name("hieudan")
            .dob(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .build();

    Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> userService.updateUser(userDto.getEmail(), userDto))
        .hasMessageContaining(String.format("No such user with %s: %s", "email", email));
  }

  @Test
  void GivenUser_TryDelete_WhenUserEmailNotExisted() {

    String email = "hieudan@gmail.com";

    User expectedUser = User.builder().email(email).name("hieudan").dob(LocalDate.now()).build();
    Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(expectedUser));

    userService.deleteUser(email);
    Mockito.verify(userRepository).delete(expectedUser);
  }

  @Test
  void GivenEmail_TryDelete_WhenUserEmailExisted_ThenThrowException() {

    String email = "hieudan@gmail.com";

    Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> userService.deleteUser(email))
        .hasMessageContaining(String.format("No such user with %s: %s", "email", email));
  }
}
