package com.fullstack.domain.port.repository;

import com.fullstack.domain.model.User;

public interface UserRepository {

  User save(User user);

  void delete(Long id);

}
