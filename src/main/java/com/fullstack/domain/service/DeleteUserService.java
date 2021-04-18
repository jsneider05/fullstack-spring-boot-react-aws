package com.fullstack.domain.service;

import com.fullstack.domain.port.dao.UserDao;
import com.fullstack.domain.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteUserService {

  private final UserDao dao;
  private final UserRepository repository;

  public void execute(Long id) {
    validateWhetherExists(id);
    this.repository.delete(id);
  }

  private void validateWhetherExists(Long id) {
    if (!this.dao.existsById(id)) {
      throw new IllegalStateException(String.format("User by %s doesn't exists", id));
    }
  }

}
