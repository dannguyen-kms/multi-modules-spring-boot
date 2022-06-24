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
    public void AddNewUser(
            @RequestBody UserDTO userDTO
    ){
        userService.AddNewUser(userDTO);
    }

    @PutMapping("{email}")
    public void UpdateUser(
            @PathVariable("email") String email,
            @RequestBody UserDTO userDTO
    ){
        userService.UpdateUser(email,userDTO);
    }

}
