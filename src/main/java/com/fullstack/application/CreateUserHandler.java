package com.fullstack.application;

import com.fullstack.application.factory.UserFactory;
import com.fullstack.application.factory.UserRequest;
import com.fullstack.domain.model.User;
import com.fullstack.domain.service.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateUserHandler {

  private final CreateUserService service;
  private final UserFactory factory;

  public User execute(UserRequest request) {
    return this.service.addUser(
        this.factory.toUser.apply(request));
  }

}
