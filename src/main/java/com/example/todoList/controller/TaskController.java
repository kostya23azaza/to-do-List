package com.example.todoList.controller;

import com.example.todoList.entity.Task;
import com.example.todoList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

  private final TaskService taskService;

  @GetMapping(value = "/get/{taskId}")
  public Task getTaskById(@PathVariable("taskId") Long taskId) {
    Task task = taskService.getTaskById(taskId);
    task.add(linkTo(methodOn(TaskController.class)
      .getTaskById(taskId))
      .withSelfRel());
    return task;
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Task create(@RequestBody Task task) {
    Task newTask = taskService.create(task);
    newTask.add(linkTo(methodOn(TaskController.class)
      .create(task))
      .withSelfRel());
    return newTask;
  }

  @DeleteMapping("/delete/{taskId}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("taskId") Long taskId) {
    taskService.deleteTaskById(taskId);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public Task update(@RequestBody Task task) {
    Task updatedTask = taskService.update(task);
    updatedTask.add(linkTo(methodOn(TaskController.class)
      .update(task))
      .withSelfRel());
    return updatedTask;
  }

  @GetMapping("/all")
  public CollectionModel<Task> getAll() {
    List<Task> allTasks = taskService.getAll();
    allTasks.forEach(task -> {
      Long taskId = task.getId();
      Link selfLink = linkTo(TaskController.class).slash(taskId).withSelfRel();
      task.add(selfLink);
    });
    Link link = linkTo(TaskController.class).withSelfRel();
    return CollectionModel.of(allTasks, link);
  }
}
