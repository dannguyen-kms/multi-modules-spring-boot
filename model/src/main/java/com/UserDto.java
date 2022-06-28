package com;

import lombok.Builder;
import lombok.Data;

/** A payload hidden id for request and response. */
@Data
@Builder
public class UserDto {
  private String name;
  private String email;
  private String dob;
}
