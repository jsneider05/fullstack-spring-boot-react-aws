package com.fullstack.infrastructure.controller;

import com.fullstack.application.CreateUserHandler;
import com.fullstack.application.DeleteUserHandler;
import com.fullstack.application.QueryUserHandler;
import com.fullstack.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
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

  @PostMapping
  public void addUser(@RequestBody User user) {
    this.createUserHandler.execute(user);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteUser(@PathVariable(value = "id") Long id) {
    this.deleteUserHandler.execute(id);
  }
}
