package com;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService{
    User findUserByEmail(String email) throws UserNotFoundException;
    void addNewUser(UserDTO userDTO);
    List<User> getAllUsers();
    void updateUser(String email, UserDTO userDTO);
    void deleteUser(String email);
}
