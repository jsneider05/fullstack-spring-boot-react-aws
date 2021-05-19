package com.fullstack.infrastructure.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.fullstack.application.CreateUserHandler;
import com.fullstack.application.DeleteUserHandler;
import com.fullstack.application.QueryUserHandler;
import com.fullstack.application.factory.UserRequest;
import com.fullstack.domain.model.User;
import com.fullstack.infrastructure.configuration.error.Error;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@ControllerAdvice
@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Administrative", description = "User administration")
public class UserController {

  private final QueryUserHandler queryUserHandler;
  private final CreateUserHandler createUserHandler;
  private final DeleteUserHandler deleteUserHandler;

  @Operation(summary = "List Users", description = "List users information", tags = {"user"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "successful operation",
          content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
      @ApiResponse(responseCode = "400", description = "exception",
          content = @Content(schema = @Schema(implementation = Error.class)))})
  @GetMapping
  public List<User> getAllUsers() {
    return this.queryUserHandler.getAllUsers();
  }

  @Operation(summary = "Save User", description = "Save user information", tags = {"user"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "successful operation",
          content = @Content(schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "400", description = "exception",
          content = @Content(schema = @Schema(implementation = Error.class)))})
  @ResponseStatus(CREATED)
  @PostMapping
  public User addUser(@Valid @RequestBody UserRequest userRequest) {
    return this.createUserHandler.execute(userRequest);
  }

  @Operation(summary = "Delete User", description = "Delete user information", tags = {"user"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "successful operation"),
      @ApiResponse(responseCode = "400", description = "exception",
          content = @Content(schema = @Schema(implementation = Error.class)))})
  @DeleteMapping(path = "/{id}")
  public void deleteUser(@PathVariable(value = "id") @Min(1) Long id) {
    this.deleteUserHandler.execute(id);
  }
}
