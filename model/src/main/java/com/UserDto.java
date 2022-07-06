package com;

import com.validation.BirtDate;
import com.validation.StartWith;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/** A payload hidden id for request and response. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
  @NotNull
  @StartWith(value = "KMS_")
  private String name;

  @NotNull(message = "email must not null")
  @Email
  private String email;

  @BirtDate
  @NotNull(message = "BirtDate must not null")
  private String dob;

  @NotNull(message = "Password must not null")
  private String password;

  @NotNull(message = "Role must not null")
  private String role;
}
