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
        User user = User.builder().setName("Dan").setDob(LocalDate.now()).setEmail("hieudankaz@gmail.com").build();
        return List.of(user);
    }
    @PostMapping
    public void AddNewUser(
            @RequestBody UserDTO userDTO
    ){
        LocalDate UserDoB = LocalDate.parse(userDTO.getDob(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        User user = User.builder().setName(userDTO.getName()).setDob(UserDoB).setEmail(userDTO.getEmail()).build();
        userService.AddNewUser(user);
    }

}
