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
    @Override
    public void AddNewUser(User user) {
        Optional<User> userExist = userRepository.findByEmail(user.getEmail());
        if(userExist.isPresent())
            throw new IllegalStateException("Email already taken!");
        else
            userRepository.save(user);

    }


}
