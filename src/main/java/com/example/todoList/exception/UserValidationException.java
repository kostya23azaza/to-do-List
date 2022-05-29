package com.example.todoList.exception;

public class UserValidationException extends RuntimeException {
  public UserValidationException(String errorMessage) {
    super(errorMessage);
  }
}
