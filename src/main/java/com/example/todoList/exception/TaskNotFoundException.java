package com.example.todoList.exception;

public class TaskNotFoundException extends RuntimeException {

  public TaskNotFoundException(String errorMessage) {
    super(errorMessage);
  }

}
