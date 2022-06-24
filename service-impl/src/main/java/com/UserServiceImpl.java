package com;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public User findUserByEmail(String email)throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty())
            throw new UserNotFoundException("email",email);
        else
            return optionalUser.get();

    }
    @Override
    public void addNewUser(UserDTO userDTO) {
        User user = userMapper.INSTANCE.toEntity(userDTO);
        Optional<User> userExist = userRepository.findByEmail(userDTO.getEmail());
        if(userExist.isPresent())
            throw new IllegalStateException("Email already taken!");
        else
            userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty())
            throw new IllegalStateException("Not found any users");

        return userList;
    }

    @Override
    public void updateUser(String email, UserDTO userDTO){
        User user = findUserByEmail(email);
        userMapper.INSTANCE.updateEntity(userDTO,user);
        userRepository.save(user);
    }
    @Override
    public void deleteUser(String email){
        User user = findUserByEmail(email);
        userRepository.delete(user);
    }
}
