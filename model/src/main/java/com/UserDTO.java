package com;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class UserDTO {
    private String name;
    private String email;
    private String dob;
}
