package com;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/** Controller provide API for using user service. */
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/user")
public class UserController {
  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok().body(userService.getAllUsers());
  }

  @GetMapping("{email}")
  public ResponseEntity<UserDto> getUser(@PathVariable("email") String email) {
    return ResponseEntity.ok().body(userMapper.INSTANCE.toDto(userService.findUserByEmail(email)));
  }

  @PostMapping
  public ResponseEntity addNewUser(@Valid @RequestBody UserDto userDto) {
    userService.addNewUser(userDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("{email}")
  public ResponseEntity updateUser(
      @PathVariable("email") String email, @RequestBody UserDto userDto) {
    userService.updateUser(email, userDto);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @DeleteMapping("{email}")
  public ResponseEntity deleteUser(@PathVariable("email") String email) {
    userService.deleteUser(email);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
