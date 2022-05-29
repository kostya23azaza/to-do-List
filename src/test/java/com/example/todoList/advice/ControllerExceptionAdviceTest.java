package com.example.todoList.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.todoList.dto.ExceptionDto;
import com.example.todoList.exception.TaskNotFoundException;
import com.example.todoList.exception.UserNotFoundException;
import com.example.todoList.exception.UserValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;


public class ControllerExceptionAdviceTest {

  private ExceptionDto exceptionDto;
  private final ControllerExceptionAdvice controllerExceptionAdvice =
    new ControllerExceptionAdvice();

  @Test
  void handleTaskNotFoundExceptionTest() {
    exceptionDto = ExceptionDto.builder()
      .httpStatus(HttpStatus.NOT_FOUND)
      .message("TaskNotFoundException message")
      .build();

    assertEquals(exceptionDto, controllerExceptionAdvice.handleTaskNotFoundException(
      new TaskNotFoundException("TaskNotFoundException message")));
  }

  @Test
  void handleUserNotFoundExceptionTest() {
    exceptionDto = ExceptionDto.builder()
      .httpStatus(HttpStatus.NOT_FOUND)
      .message("UserNotFoundException message")
      .build();

    assertEquals(exceptionDto, controllerExceptionAdvice.handleUserNotFoundException(
      new UserNotFoundException("UserNotFoundException message")));
  }

  @Test
  void handleUserValidationExceptionTest() {
    exceptionDto = ExceptionDto.builder()
      .httpStatus(HttpStatus.BAD_REQUEST)
      .message("UserValidationException message")
      .build();

    assertEquals(exceptionDto, controllerExceptionAdvice.handleUserValidationException(
      new UserValidationException("UserValidationException message")));
  }
}
