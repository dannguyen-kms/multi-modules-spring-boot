package com;

import java.util.List;
import org.springframework.stereotype.Service;

/** Interface business logic for user. */
@Service
public interface UserService {
  User findUserByEmail(String email) throws UserNotFoundException;

  void addNewUser(UserDto userDtO);

  List<User> getAllUsers();

  void updateUser(String email, UserDto userDtO);

  void deleteUser(String email);
}
