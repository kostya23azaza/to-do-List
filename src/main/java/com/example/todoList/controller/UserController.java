package com.example.todoList.controller;

import com.example.todoList.entity.User;
import com.example.todoList.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  private final UserService userService;

  @PostMapping("/registration")
  @ResponseStatus(HttpStatus.CREATED)
  public void addUser(@RequestBody User user) {
    userService.save(user);
  }

  @GetMapping(value = "/get/{id}")
  @ResponseStatus(HttpStatus.OK)
  public User getUserById(@PathVariable Long id) {
    User user = userService.getUserById(id);
    user.add(linkTo(methodOn(UserController.class)
      .getUserById(id))
      .withSelfRel());
    return user;
  }

  @GetMapping(value = "/all")
  @ResponseStatus(HttpStatus.OK)
  public CollectionModel<User> getAllUsers() {
    List<User> allUsers = userService.getAll();
    allUsers.forEach(user -> {
      Long userId = user.getId();
      Link selfLink = linkTo(UserController.class).slash(userId).withSelfRel();
      user.add(selfLink);
    });
    Link link = linkTo(UserController.class).withSelfRel();
    return CollectionModel.of(allUsers, link);
  }
}
