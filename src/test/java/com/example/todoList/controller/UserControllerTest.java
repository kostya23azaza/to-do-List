package com.example.todoList.controller;

import com.example.todoList.dto.UserDto;
import com.example.todoList.entity.User;
import com.example.todoList.service.UserService;
import com.example.todoList.util.converter.UserToUserDtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

  @InjectMocks
  private UserController userController;

  @Mock
  private UserService userService;
  @Mock
  private UserToUserDtoConverter converter;

  private User user;

  private UserDto userDto;

  @BeforeEach
  public void setUp() {
    user = new User();
    user.setId(1L);
    user.setUsername("username");
    user.setPassword("test");
    userDto = UserDto
      .builder()
      .id(user.getId())
      .tasks(user.getTasks())
      .role(user.getRoles())
      .email(user.getEmail())
      .username(user.getUsername())
      .build();
  }

  @Test
  public void getUserByIdTest() {
    when(userService.getUserById(1L)).thenReturn(user);
    when(converter.convert(user)).thenReturn(userDto);
    assertEquals(userController.getUserById(1L), userDto);
    verify(userService).getUserById(1L);
  }

  @Test
  public void getAllUsersTest() {
    when(userService.getAll()).thenReturn(List.of(user));
    when(converter.convert(user)).thenReturn(userDto);
    List<UserDto> userDtoList = Stream.of(user).map(converter::convert).collect(Collectors.toList());
    assertEquals(new ArrayList<>(userController.getAll().getContent()), userDtoList);
    verify(userService).getAll();
  }

}
