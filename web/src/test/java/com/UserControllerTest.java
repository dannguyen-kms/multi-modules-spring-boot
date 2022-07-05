package com;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
  @Mock private UserService userService;
  @Mock UserMapper userMapper;

  private UserController userController;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    userController = new UserController(userService, userMapper);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  void getUsers_WhenUsersExit_thenReturnJsonListUsers() throws Exception {
    List<User> provideList =
        List.of(
            User.builder().email("1@gmail.com").name("hieudan").dob(LocalDate.now()).build(),
            User.builder().email("2@gmail.com").name("hieudan").dob(LocalDate.now()).build(),
            User.builder().email("3@gmail.com").name("hieudan").dob(LocalDate.now()).build(),
            User.builder().email("4@gmail.com").name("hieudan").dob(LocalDate.now()).build(),
            User.builder().email("5@gmail.com").name("hieudan").dob(LocalDate.now()).build());

    Mockito.when(userService.getAllUsers()).thenReturn(provideList);
    String uri = "/api/v1/user";
    mockMvc
        .perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(provideList.size())));
  }

  //  @Test // Can't do because not yet handle exception
  //  void getUsers_WhenUsersNotExit_thenReturnNotFound() throws Exception {
  //
  //    Mockito.when(userService.getAllUsers())
  //        .thenThrow(new IllegalStateException("Not found any users"));
  //    String uri = "/api/v1/user";
  //    mockMvc
  //        .perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
  //        .andExpect(status().isOk());
  //  }

  @Test
  void givenUserEmail_WhenUsersExit_thenReturnJsonListUsers() throws Exception {
    String email = "hieudan@gmail.com";
    User expectedUser = User.builder().email(email).name("hieudan").dob(LocalDate.now()).build();

    Mockito.when(userService.findUserByEmail(email)).thenReturn(expectedUser);
    String uri = "/api/v1/user/" + expectedUser.getEmail();
    mockMvc
        .perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

  //  @Test
  //  void givenUserJson_TryToInsert_WhenUserNotExit() throws Exception {
  //    String email = "hieudan@gmail.com";
  //    UserDto userDto =
  // UserDto.builder().password("123456789").role("ADMIN").email(email).name("hieudan").dob("26/06/2001").build();
  //    Gson gson = new Gson();
  //    String jsonUserDto = gson.toJson(userDto);
  //    String uri = "/api/v1/user";
  //    mockMvc
  //        .perform(
  //            MockMvcRequestBuilders.post(uri)
  //                .contentType(MediaType.APPLICATION_JSON_VALUE)
  //                .content(jsonUserDto))
  //        .andExpect(status().isCreated());
  //
  //    Mockito.verify(userService).addNewUser(userDto);
  //  }

  @Test
  void givenUserJson_TryToUpdate_WhenUserNotExit() throws Exception {
    String email = "hieudan@gmail.com";
    UserDto userDto = UserDto.builder().email(email).name("hieudan").dob("26/06/2001").build();
    Gson gson = new Gson();
    String jsonUserDto = gson.toJson(userDto);
    String uri = "/api/v1/user/" + email;
    mockMvc
        .perform(
            MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonUserDto))
        .andExpect(status().isNoContent());
    Mockito.verify(userService).updateUser(email, userDto);
  }

  @Test
  void givenUserJson_TryToDelete_WhenUserNotExit() throws Exception {
    String email = "hieudan@gmail.com";
    String uri = "/api/v1/user/" + email;
    mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(status().isNoContent());
    Mockito.verify(userService).deleteUser(email);
  }
}
