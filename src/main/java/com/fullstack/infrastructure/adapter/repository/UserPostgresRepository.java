package com.fullstack.infrastructure.adapter.repository;

import com.fullstack.domain.model.User;
import com.fullstack.domain.port.repository.UserRepository;
import com.fullstack.infrastructure.adapter.UserJpaRepository;
import com.fullstack.infrastructure.adapter.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserPostgresRepository implements UserRepository {

  private final UserJpaRepository repository;
  private final UserMapper mapper;

  @Override
  public void save(User user) {
    this.repository.save(
        this.mapper.toUserEntity.apply(user)
    );
  }

  @Override
  public void delete(Long id) {
    this.repository.deleteById(id);
  }
}
