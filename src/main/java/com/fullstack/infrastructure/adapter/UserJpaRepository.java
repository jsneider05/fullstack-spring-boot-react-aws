package com.fullstack.infrastructure.adapter;

import com.fullstack.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

  @Query("" +
      "SELECT CASE WHEN COUNT(u) > 0 THEN " +
      "TRUE ELSE FALSE END " +
      "FROM UserEntity u " +
      "WHERE u.email = :email"
  )
  Boolean selectExistsByEmail(@Param(value = "email") String email);

}
