package com.fullstack.application;

import com.fullstack.domain.model.User;
import com.fullstack.domain.service.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateUserHandler {

  private final CreateUserService service;

  public void execute(User user) {
    this.service.addUser(user);
  }

}
