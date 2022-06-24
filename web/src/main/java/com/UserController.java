package com;
import com.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/student")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }
    @PostMapping
    public void addNewUser(
            @RequestBody UserDTO userDTO
    ){
        userService.addNewUser(userDTO);
    }

    @PutMapping("{email}")
    public void updateUser(
            @PathVariable("email") String email,
            @RequestBody UserDTO userDTO
    ){
        userService.updateUser(email,userDTO);
    }

    @DeleteMapping ("{email}")
    public void deleteUser(
            @PathVariable("email") String email
    ){
        userService.deleteUser(email);
    }
}
