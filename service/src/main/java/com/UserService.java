package com;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService{
    User findUserByEmail(String email) throws UserNotFoundException;
    void AddNewUser(User user);
    List<User> getAllUsers();
}
