package com;

import org.springframework.stereotype.Service;

@Service
public interface UserService{
    User findUserByEmail(String email) throws UserNotFoundException;
    void AddNewUser(User user);
}
