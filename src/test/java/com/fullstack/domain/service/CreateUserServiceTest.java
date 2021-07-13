package com.fullstack.domain.service;

import static com.fullstack.domain.builder.UserBuilder.*;
import static org.mockito.Mockito.verify;

import com.fullstack.domain.model.User;
import com.fullstack.domain.port.dao.UserDao;
import com.fullstack.domain.port.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

  private CreateUserService underTest;

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserDao userDao;

  @BeforeEach
  void setUp() {
    underTest = new CreateUserService(userRepository, userDao);
  }

  @Test
  void cadAddUserSuccess() {
    // Given
    User user = newUser()
        .build();

    // When
    underTest.addUser(user);

    // Then
    verify(userDao).existsByEmail(user.getEmail());
  }
}