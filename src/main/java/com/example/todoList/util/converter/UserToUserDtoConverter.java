package com.example.todoList.util.converter;

import com.example.todoList.dto.UserDto;
import com.example.todoList.entity.User;
import com.example.todoList.util.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToUserDtoConverter implements Converter<UserDto, User> {

  @Override
  public UserDto convert(User user) {
    return UserDto
      .builder()
      .id(user.getId())
      .username(user.getUsername())
      .email(user.getEmail())
      .role(user.getRoles())
      .tasks(user.getTasks())
      .build();
  }
}
