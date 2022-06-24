package com;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/student")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok()
                .body(userService.getAllUsers());
    }

    @GetMapping("{email}")
    public ResponseEntity<UserDTO> getUser(
            @PathVariable("email") String email
    ) {
        return ResponseEntity.ok()
                .body(userMapper.INSTANCE.toDto(userService.findUserByEmail(email)));
    }

    @PostMapping
    public ResponseEntity addNewUser(
            @RequestBody UserDTO userDTO
    ) {
        userService.addNewUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{email}")
    public ResponseEntity updateUser(
            @PathVariable("email") String email,
            @RequestBody UserDTO userDTO
    ) {
        userService.updateUser(email, userDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("{email}")
    public ResponseEntity deleteUser(
            @PathVariable("email") String email
    ) {
        userService.deleteUser(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
