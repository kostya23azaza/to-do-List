package com.example.todoList.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.todoList.entity.User;
import com.example.todoList.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

  @InjectMocks
  private UserController userController;

  @Mock
  private UserService userService;
  private User user;

  @BeforeEach
  public void setUp() {
    user = new User();
    user.setId(1L);
    user.setUsername("username");
    user.setPassword("test");
  }

  @Test
  public void getUserByIdTest() {
    when(userService.getUserById(1L)).thenReturn(user);
    assertEquals(userController.getUserById(1L), user);
    verify(userService).getUserById(1L);
  }

  @Test
  public void getAllUsersTest() {
    when(userService.getAllUsers()).thenReturn(List.of(user));
    assertEquals(new ArrayList<>(userController.getAllUsers().getContent()), List.of(user));
    verify(userService).getAllUsers();
  }

  @Test
  public void addUserTest() {
    when(userService.saveUser(user)).thenReturn(true);
    assertEquals(userController.addUser(user).getStatusCode(), HttpStatus.CREATED);
    verify(userService).saveUser(user);
  }

}
