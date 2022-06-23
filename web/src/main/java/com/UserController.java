package com;
import com.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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


}
