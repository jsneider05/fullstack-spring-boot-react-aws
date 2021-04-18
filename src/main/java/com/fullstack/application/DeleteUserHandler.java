package com.fullstack.application;

import com.fullstack.domain.service.DeleteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteUserHandler {

  private final DeleteUserService service;

  public void execute(Long id) {
    this.service.execute(id);
  }
}
