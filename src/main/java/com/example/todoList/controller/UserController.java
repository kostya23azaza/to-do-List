package com.example.todoList.controller;

import com.example.todoList.entity.User;
import com.example.todoList.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  private final UserService userService;

  @PostMapping("/registration")
  public boolean addUser(@RequestBody User user) {
    userService.saveUser(user);
    return true;
  }

  @GetMapping(value = "/get/{id}")
  public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @GetMapping(value = "/all")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }
}
