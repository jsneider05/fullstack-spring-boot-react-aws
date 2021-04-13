package com.fullstack.domain.service;

import com.fullstack.domain.model.User;
import com.fullstack.domain.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateUserService {

  private final UserRepository userRepository;

  public void addUser(User user) {
    this.userRepository.save(user);
  }

}
