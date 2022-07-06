package com;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/** Business logic for User entity. */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.findUserByEmail(username);

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole()));
    return new org.springframework.security.core.userdetails.User(
        username, user.getPassword(), authorities);
  }

  @Override
  public User findUserByEmail(final String email) throws UserNotFoundException {
    Optional<User> optionalUser = userRepository.findByEmail(email);

    if (optionalUser.isEmpty()) {
      log.error(String.format("No such user with %s: %s", "email", email));
      throw new UserNotFoundException("email", email);
    }
    log.info(String.format("Founded user with %s: %s", "email", email));
    return optionalUser.get();
  }

  @Override
  public void addNewUser(final UserDto userDto) {
    User user = userMapper.INSTANCE.toEntity(userDto);
    Optional<User> userExist = userRepository.findByEmail(userDto.getEmail());

    if (userExist.isPresent()) {
      log.error(String.format("Email already taken when use: %s", userDto.getEmail()));
      throw new IllegalStateException("Email already taken!");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    log.info(String.format("Created user with email: %s", userDto.getEmail()));
  }

  @Override
  public List<User> getAllUsers() {
    List<User> userList = userRepository.findAll();
    if (userList.isEmpty()) {
      log.error("Not found any users");
      throw new IllegalStateException("Not found any users");
    }
    log.info(String.format("Get %d users", userList.stream().count()));

    return userList;
  }

  @Override
  public void updateUser(final String email, final UserDto userDto) {
    User user = findUserByEmail(email);
    userMapper.INSTANCE.updateEntity(userDto, user);
    userRepository.save(user);
    log.info(String.format("Update user with email: %s ", email));
  }

  @Override
  public void deleteUser(final String email) {
    User user = findUserByEmail(email);
    userRepository.delete(user);
    log.info(String.format("Deleted user with email: %s ", email));
  }
}
