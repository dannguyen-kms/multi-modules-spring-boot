package com;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public User findUserByEmail(String email)throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty())
            throw new UserNotFoundException("email",email);
        else
            return optionalUser.get();

    }
}
