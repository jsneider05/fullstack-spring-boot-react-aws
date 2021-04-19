package com.fullstack.infrastructure.configuration.error;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Error {

  private LocalDateTime dateTime;
  private int statusCode;
  private String statusText;
  private List<String> message;

}
