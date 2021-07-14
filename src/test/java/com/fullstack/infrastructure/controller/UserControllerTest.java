package com.fullstack.infrastructure.controller;

import static com.fullstack.domain.builder.UserBuilder.newUser;
import static com.fullstack.domain.model.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fullstack.application.factory.UserRequest;
import com.fullstack.configuration.ApplicationMock;
import com.fullstack.domain.model.User;
import com.github.javafaker.Faker;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = ApplicationMock.class)
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

  private static final String URL = "/api/v1/user";
  private static final String URL_DELETE = "/api/v1/user/{id}";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private ObjectWriter objectWriter;

  private Faker faker;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    faker = new Faker();
  }

  @Test
  void getAllUsersSuccess() throws Exception {
    // Given
    User userExpected = newUser()
        .withId(1L)
        .withName("joan")
        .withEmail("joan@gmail.com")
        .withGender(MALE)
        .build();

    // When
    ResultActions resultActions = mockMvc.perform(get(URL)
        .contentType(MediaType.APPLICATION_JSON));

    List<User> userListResponse = objectMapper.readValue(
        resultActions.andReturn().getResponse().getContentAsString(),
        new TypeReference<>() {
        });

    // Then
    resultActions.andExpect(status().isOk());

    assertThat(userListResponse)
        .isNotEmpty()
        .hasSizeGreaterThan(0)
        .usingFieldByFieldElementComparator().contains(userExpected);
  }

  @Test
  void addUserSuccess() throws Exception {
    // Given
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String name = String.format("%s %s", firstName, lastName);
    String email = String.format("%s.%s@gmail.com", firstName, lastName);
    UserRequest userRequest = UserRequest.builder()
        .name(name)
        .email(email)
        .gender(MALE.toString())
        .build();

    String userRequestJson = objectWriter.writeValueAsString(userRequest);

    // When
    ResultActions resultActions = mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(userRequestJson));

    User userResponse = objectMapper.readValue(
        resultActions.andReturn().getResponse().getContentAsString(), User.class);

    // Then
    resultActions.andExpect(status().isCreated());

    assertThat(userResponse).isNotNull();
    assertThat(userResponse.getId()).isGreaterThan(1L);
    assertThat(userResponse.getName()).isEqualTo(name);
    assertThat(userResponse.getEmail()).isEqualTo(email);
    assertThat(userResponse.getGender()).isEqualTo(MALE);
  }

  @Test
  void deleteUserSuccess() throws Exception {
    // Given
    long userId = 1L;

    // When
    ResultActions resultActions = mockMvc.perform(delete(URL_DELETE, userId)
        .contentType(MediaType.APPLICATION_JSON));

    // Then
    resultActions.andExpect(status().isOk());
  }
}