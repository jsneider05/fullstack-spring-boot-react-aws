package com.fullstack.infrastructure.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.fullstack.application.CreateUserHandler;
import com.fullstack.application.DeleteUserHandler;
import com.fullstack.application.QueryUserHandler;
import com.fullstack.domain.model.User;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@ControllerAdvice
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final QueryUserHandler queryUserHandler;
  private final CreateUserHandler createUserHandler;
  private final DeleteUserHandler deleteUserHandler;

  @GetMapping
  public List<User> getAllUsers() {
    return this.queryUserHandler.getAllUsers();
  }

  @ResponseStatus(CREATED)
  @PostMapping
  public User addUser(@Valid @RequestBody User user) {
    return this.createUserHandler.execute(user);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteUser(@PathVariable(value = "id") Long id) {
    this.deleteUserHandler.execute(id);
  }
}
