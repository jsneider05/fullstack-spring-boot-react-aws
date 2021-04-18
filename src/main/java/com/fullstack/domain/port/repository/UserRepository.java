package com.fullstack.domain.port.repository;

import com.fullstack.domain.model.User;

public interface UserRepository {

  void save(User user);

  void delete(Long id);

}
