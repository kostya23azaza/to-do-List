package com.example.todoList.util;

public interface Converter<U, T> {
  U convert(T t);
}
