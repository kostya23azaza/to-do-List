package com.example.todoList.service;

import com.example.todoList.entity.Role;
import com.example.todoList.entity.User;
import com.example.todoList.exception.UserNotFoundException;
import com.example.todoList.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;
  private User user;
  private Role role;

  @BeforeEach
  public void setUp() {
    role = new Role();
    role.setId(1L);
    role.setName("user");
    user = new User();
    user.setId(1L);
    user.setUsername("user");
    user.setPassword("test");
    user.setEmail("user@email.ru");
    user.setTasks(new ArrayList<>());
    user.setRoles(Set.of(role));
  }

  @Test
  public void loadByUserNameTest() {
    String userName = "username";
    when(userRepository.findByUsername(userName)).thenReturn(Optional.of(user));

    org.springframework.security.core.userdetails.User userDetails =
      new org.springframework.security.core.userdetails.User
        (user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(role.getName())));

    assertEquals(userDetails, userService.loadUserByUsername(userName));
    verify(userRepository).findByUsername(userName);
  }

  @Test
  public void loadByUserNameWhenUserNotFoundExceptionWasThrown() {
    String userName = "wrong name";
    when(userRepository.findByUsername(userName)).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> userService.loadUserByUsername(userName));
    verify(userRepository).findByUsername(userName);
  }

  @Test
  public void saveUserTest() {
    when(userRepository.save(user)).thenReturn(user);
    assertEquals(user, userService.save(user));
    verify(userRepository).save(user);
  }

  @Test
  public void getUserById() {
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    User userById = userService.getUserById(1L);
    assertEquals(user, userById);
    verify(userRepository).findById(1L);
  }

  @Test
  public void getUserByIdWhenUserNotFoundExceptionWasThrown() {
    when(userRepository.findById(5L)).thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> userService.getUserById(5L));
    verify(userRepository).findById(5L);
  }

  @Test
  public void getAllUsersTest() {
    userService.getAll();
    verify(userRepository).findAll();
  }


}
