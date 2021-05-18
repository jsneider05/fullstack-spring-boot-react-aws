package com.fullstack.infrastructure.adapter.mapper;

import com.fullstack.domain.model.User;
import com.fullstack.infrastructure.entity.UserEntity;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public final class UserMapper {

  public final Function<UserEntity, User> toUser = entity ->
      new User(
          entity.getId(),
          entity.getName(),
          entity.getEmail(),
          entity.getGender());

  public final Function<List<UserEntity>, List<User>> toUserList = userEntities ->
      userEntities.stream()
          .map(this.toUser)
          .collect(Collectors.toList());

  public final Function<User, UserEntity> toUserEntity = user ->
      UserEntity.builder()
          .name(user.getName())
          .email(user.getEmail())
          .gender(user.getGender())
          .build();

}
