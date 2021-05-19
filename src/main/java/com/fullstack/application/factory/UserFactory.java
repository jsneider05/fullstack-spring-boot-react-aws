package com.fullstack.application.factory;

import com.fullstack.domain.model.Gender;
import com.fullstack.domain.model.User;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

  public final Function<UserRequest, User> toUser = request ->
      new User(
          request.getId(),
          request.getName(),
          request.getEmail(),
          Gender.valueOf(request.getGender()));

}
