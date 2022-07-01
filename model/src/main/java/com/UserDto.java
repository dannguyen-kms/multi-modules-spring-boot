package com;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/** A payload hidden id for request and response. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
  @NotNull
  @StartWith(value = "KMS_")
  private String name;

  @NotNull @Email private String email;

  @BirtDate @NotNull private String dob;
}
