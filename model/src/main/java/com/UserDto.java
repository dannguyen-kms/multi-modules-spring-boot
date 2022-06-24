package com;

import lombok.Data;

/** A payload hidden id for request and response. */
@Data
public class UserDto {
  private String name;
  private String email;
  private String dob;
}
