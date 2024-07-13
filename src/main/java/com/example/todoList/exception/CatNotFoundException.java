package com.example.todoList.exception;

public class CatNotFoundException extends RuntimeException {

    public CatNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
