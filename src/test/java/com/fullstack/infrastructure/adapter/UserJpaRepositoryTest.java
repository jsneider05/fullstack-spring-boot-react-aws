package com.fullstack.infrastructure.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
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