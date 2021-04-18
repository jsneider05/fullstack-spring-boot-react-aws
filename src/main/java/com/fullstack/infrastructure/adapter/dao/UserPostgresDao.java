package com.fullstack.infrastructure.adapter.dao;

import com.fullstack.domain.model.User;
import com.fullstack.domain.port.dao.UserDao;
import com.fullstack.infrastructure.adapter.UserJpaRepository;
import com.fullstack.infrastructure.adapter.mapper.UserMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserPostgresDao implements UserDao {

  private final UserJpaRepository repository;
  private final UserMapper mapper;

  @Override
  public List<User> getAll() {
    return this.mapper.toUserList.apply(
        this.repository.findAll()
    );
  }

  @Override
  public Boolean existsById(Long id) {
    return this.repository.existsById(id);
  }

}
