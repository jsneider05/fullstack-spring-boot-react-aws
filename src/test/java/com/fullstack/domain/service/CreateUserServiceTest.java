package com.fullstack.domain.service;

import static com.fullstack.domain.builder.UserBuilder.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import com.fullstack.domain.builder.UserBuilder;
import com.fullstack.domain.model.User;
import com.fullstack.domain.port.dao.UserDao;
import com.fullstack.domain.port.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

class CreateUserServiceTest {

  private CreateUserService underTest;

  private AutoCloseable autoCloseable;

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserDao userDao;

  @BeforeEach
  void setUp() {
    autoCloseable = MockitoAnnotations.openMocks(this);
    underTest = new CreateUserService(userRepository, userDao);
  }

  @AfterEach
  void tearDown() throws Exception {
    autoCloseable.close();
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