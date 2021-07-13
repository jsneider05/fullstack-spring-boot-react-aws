package com.fullstack.domain.service;

import static com.fullstack.domain.builder.UserBuilder.*;
import static com.fullstack.domain.model.Gender.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

import com.fullstack.domain.model.User;
import com.fullstack.domain.port.dao.UserDao;
import com.fullstack.domain.port.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
  void canAddUserSuccess() {
    // Given
    User user = newUser()
        .withName("joan")
        .withEmail("joan@gmail.com")
        .withGender(MALE)
        .build();

    // When
    underTest.addUser(user);

    // Then
    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
    verify(userRepository).save(userArgumentCaptor.capture());

    User capturedUser = userArgumentCaptor.getValue();

    assertThat(capturedUser).isEqualTo(user);
  }
}