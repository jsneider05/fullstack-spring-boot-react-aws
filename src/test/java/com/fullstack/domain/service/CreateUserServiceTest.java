package com.fullstack.domain.service;

import static com.fullstack.domain.builder.UserBuilder.newUser;
import static com.fullstack.domain.model.Gender.MALE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fullstack.domain.model.User;
import com.fullstack.domain.port.dao.UserDao;
import com.fullstack.domain.port.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

  private static final String EMAIL_ALREADY_EXISTS = "Email %s already exists";
  private static final int ONE_INVOCATION = 1;

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
  void canAddUser() {
    // Given
    User user = newUser()
        .withName("joan")
        .withEmail("joan@gmail.com")
        .withGender(MALE)
        .build();

    given(userDao.existsByEmail(anyString())).willReturn(FALSE);

    // When
    underTest.addUser(user);

    // Then
    InOrder order = inOrder(userDao, userRepository);
    order.verify(userDao, times(ONE_INVOCATION)).existsByEmail(user.getEmail());
    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
    order.verify(userRepository, times(ONE_INVOCATION)).save(userArgumentCaptor.capture());

    User capturedUser = userArgumentCaptor.getValue();

    assertThat(capturedUser).isEqualTo(user);
  }

  @Test
  void willThrowWhenAddUserWithEmailIsTaken() {
    // Given
    User user = newUser()
        .withName("joan")
        .withEmail("joan@gmail.com")
        .withGender(MALE)
        .build();

    given(userDao.existsByEmail(anyString())).willReturn(TRUE);

    // When
    // Then
    assertThatThrownBy(() -> underTest.addUser(user))
        .hasMessageContaining(EMAIL_ALREADY_EXISTS, user.getEmail())
        .isInstanceOf(IllegalArgumentException.class);

    verify(userDao, times(ONE_INVOCATION)).existsByEmail(user.getEmail());
    verify(userRepository, never()).save(any());
  }
}