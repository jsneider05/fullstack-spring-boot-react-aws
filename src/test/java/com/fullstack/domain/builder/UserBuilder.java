package com.fullstack.domain.builder;

import static com.fullstack.domain.model.Gender.MALE;

import com.fullstack.domain.model.Gender;
import com.fullstack.domain.model.User;

public class UserBuilder {

  private static final Long ID = 1L;
  private static final String NAME = "joan";
  private static final String EMAIL = "joan@gmail.com";
  private static final Gender GENDER = MALE;

  private Long id;
  private String name;
  private String email;
  private Gender gender;

  public UserBuilder() {
    this.id = ID;
    this.name = NAME;
    this.email = EMAIL;
    this.gender = GENDER;
  }

  public UserBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  public UserBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public UserBuilder withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserBuilder withGender(Gender gender) {
    this.gender = gender;
    return this;
  }

  public User build() {
    return new User(
        this.id,
        this.name,
        this.email,
        this.gender
    );
  }

  public static UserBuilder newUser() {
    return new UserBuilder();
  }
}
