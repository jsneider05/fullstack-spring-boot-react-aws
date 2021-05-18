package com.fullstack.domain.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

  private Long id;

  @NotBlank
  private String name;

  @Email
  private String email;

  @NotNull
  private Gender gender;

}
