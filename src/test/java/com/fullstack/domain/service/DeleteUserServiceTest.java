package com.fullstack.domain.service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fullstack.domain.exception.NoDataException;
import com.fullstack.domain.port.dao.UserDao;
import com.fullstack.domain.port.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserServiceTest {

  private static final String USER_BY_ID_NOT_EXISTS = "User by id %s doesn't exists";
  private static final int ONE_INVOCATION = 1;

  private DeleteUserService underTest;

  @Mock
  private UserDao userDao;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    underTest = new DeleteUserService(userDao, userRepository);
  }

  @Test
  void canDeleteUserById() {
    // Given
    long id = 1L;
    given(userDao.existsById(anyLong())).willReturn(TRUE);

    // When
    underTest.execute(id);

    // Then
    InOrder order = inOrder(userDao, userRepository);
    order.verify(userDao, times(ONE_INVOCATION)).existsById(anyLong());
    order.verify(userRepository, times(ONE_INVOCATION)).delete(anyLong());
  }

  @Test
  void willThrowWhenDeleteUserThatDoesNotExistsById() {
    // Given
    long id = 1L;
    given(userDao.existsById(anyLong())).willReturn(FALSE);

    // When
    // Then
    assertThatThrownBy(() -> underTest.execute(id))
        .hasMessageContaining(USER_BY_ID_NOT_EXISTS, id)
        .isInstanceOf(NoDataException.class);

    verify(userDao, times(ONE_INVOCATION)).existsById(anyLong());
    verify(userRepository, never()).delete(anyLong());
  }
}