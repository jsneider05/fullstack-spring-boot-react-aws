package com.fullstack.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

  private Long id;

  private String name;

  private String email;

  private Gender gender;

}
