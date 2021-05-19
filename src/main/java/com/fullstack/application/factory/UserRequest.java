package com.fullstack.application.factory;

import com.fullstack.domain.model.Gender;
import com.fullstack.domain.validator.annotations.EnumValid;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequest {

  @Schema(description = "Unique identifier of the User.", example = "1")
  private Long id;

  @NotBlank
  @Schema(description = "Name of the User.", example = "Joan", required = true)
  private String name;

  @Email
  @Schema(description = "Email of the User.", example = "Joan@gmail.com", required = true)
  private String email;

  @NotNull
  @EnumValid(enumClass = Gender.class)
  @Schema(description = "Gender of the User.", example = "MALE", required = true)
  private String gender;

}
