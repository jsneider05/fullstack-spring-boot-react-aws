package com.fullstack.infrastructure.entity;

import com.fullstack.domain.model.Gender;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_entity")
public class UserEntity {

  @Id
  @SequenceGenerator(
      name = "user_sequence",
      sequenceName = "user_sequence",
      allocationSize = 1)
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "user_sequence")
  private Long id;

  private String name;

  private String email;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  public UserEntity(String name, String email, Gender gender) {
    this.name = name;
    this.email = email;
    this.gender = gender;
  }

}
