package com;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** A payload hidden id for request and response. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
  private String name;
  private String email;
  private String dob;
}
