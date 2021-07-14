package com.fullstack.infrastructure.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import com.fullstack.configuration.ApplicationMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = ApplicationMock.class)
class UserJpaRepositoryTest {

  @Autowired
  private UserJpaRepository underTest;

  @Test
  void itShouldCheckWhenUserEmailExists() {
    // Given
    String email = "joan@gmail.com";

    // When
    boolean expected = underTest.selectExistsByEmail(email);

    // Then
    assertThat(expected).isTrue();
  }

  @Test
  void itShouldCheckWhenUserEmailDoesNotExists() {
    // Given
    String email = "emaildoesnotexist@gmail.com";

    // When
    boolean expected = underTest.selectExistsByEmail(email);

    // Then
    assertThat(expected).isFalse();
  }
}