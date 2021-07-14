package com.fullstack.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private Long id;

  private String name;

  private String email;

  private Gender gender;

}
