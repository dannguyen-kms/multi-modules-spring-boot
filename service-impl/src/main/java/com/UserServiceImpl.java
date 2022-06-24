package com;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public User findUserByEmail(String email)throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty())
        {
            log.error(String.format("No such user with %s: %s","email",email));
            throw new UserNotFoundException("email",email);
        }
        log.info(String.format("Founded user with %s: %s","email",email));
        return optionalUser.get();

    }
    @Override
    public void addNewUser(UserDTO userDTO) {
        User user = userMapper.INSTANCE.toEntity(userDTO);
        Optional<User> userExist = userRepository.findByEmail(userDTO.getEmail());
        if(userExist.isPresent())
        {
            log.error(String.format("Email already taken when use: %s",userDTO.getEmail()));
            throw new IllegalStateException("Email already taken!");
        }

        userRepository.save(user);
        log.info(String.format("Created user with email: %s",userDTO.getEmail()));

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty())
        {
            log.error("Not found any users");
            throw new IllegalStateException("Not found any users");
        }
        log.info(String.format("Get %d users",userList.stream().count()));
        return userList;
    }

    @Override
    public void updateUser(String email, UserDTO userDTO){
        User user = findUserByEmail(email);
        userMapper.INSTANCE.updateEntity(userDTO,user);
        userRepository.save(user);
        log.info(String.format("Update user with email: %s ",email));
    }
    @Override
    public void deleteUser(String email){
        User user = findUserByEmail(email);
        userRepository.delete(user);
        log.info(String.format("Deleted user with email: %s ",email));
    }
}
