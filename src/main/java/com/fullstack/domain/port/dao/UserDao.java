package com.fullstack.domain.port.dao;

import com.fullstack.domain.model.User;
import java.util.List;

public interface UserDao {

  List<User> getAll();

  Boolean existsById(Long id);

  Boolean existsByEmail(String email);

}
