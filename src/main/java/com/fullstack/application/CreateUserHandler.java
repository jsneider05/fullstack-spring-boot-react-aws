package com.fullstack.application;

import com.fullstack.domain.model.User;
import com.fullstack.domain.service.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateUserHandler {

  private final CreateUserService service;

  public User execute(User user) {
    return this.service.addUser(user);
  }

}
