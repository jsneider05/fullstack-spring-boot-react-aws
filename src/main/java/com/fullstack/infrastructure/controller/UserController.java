package com.fullstack.infrastructure.controller;

import static com.fullstack.domain.model.Gender.*;

import com.fullstack.domain.model.User;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @GetMapping
  public List<User> getAllUsers() {
    return Arrays.asList(
        new User(
            1L,
            "Jamila",
            "jamila@gmail.com",
            FEMALE),
        new User(
            2L,
            "Alex",
            "alex@gmail.com",
            MALE)
    );
  }
}
