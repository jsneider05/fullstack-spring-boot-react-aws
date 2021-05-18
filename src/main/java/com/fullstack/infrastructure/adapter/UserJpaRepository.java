package com.fullstack.infrastructure.adapter;

import com.fullstack.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

  Boolean existsByEmail(String email);

}
