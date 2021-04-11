package com.fullstack.application;

import com.fullstack.domain.model.User;
import com.fullstack.domain.port.dao.UserDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class QueryUserHandler {

  private final UserDao dao;

  public List<User> getAllUsers() {
    return dao.getAll();
  }
}
