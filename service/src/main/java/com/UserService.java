package com;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService{
    User findUserByEmail(String email) throws UserNotFoundException;
    void AddNewUser(UserDTO userDTO);
    List<User> getAllUsers();
    void UpdateUser(String email, UserDTO userDTO);
}
