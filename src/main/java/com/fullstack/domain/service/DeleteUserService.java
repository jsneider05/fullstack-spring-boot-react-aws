package com.fullstack.domain.service;

import com.fullstack.domain.exception.NoDataException;
import com.fullstack.domain.port.dao.UserDao;
import com.fullstack.domain.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteUserService {

  private static final String USER_BY_ID_NOT_EXISTS = "User by id %s doesn't exists";

  private final UserDao dao;
  private final UserRepository repository;

  public void execute(Long id) {

    validateWhetherExists(id);

    this.repository.delete(id);
  }

  private void validateWhetherExists(Long id) {
    if (!this.dao.existsById(id)) {
      throw new NoDataException(String.format(USER_BY_ID_NOT_EXISTS, id));
    }
  }

}
