package com.fullstack.domain.service;

import com.fullstack.domain.model.User;
import com.fullstack.domain.port.dao.UserDao;
import com.fullstack.domain.port.repository.UserRepository;
import com.fullstack.domain.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateUserService {

  private static final String EMAIL_ALREADY_EXISTS = "Email %s already exists";

  private final UserRepository userRepository;
  private final UserDao userDao;

  public User addUser(User user) {

    UserValidator.isEmailUsed(this.userDao.existsByEmail(user.getEmail()))
        .validate(user)
        .throwIfInvalid(EMAIL_ALREADY_EXISTS, user.getEmail());

    return this.userRepository.save(user);
  }

}
