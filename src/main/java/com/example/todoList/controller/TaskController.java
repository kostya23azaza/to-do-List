package com.example.todoList.controller;

import com.example.todoList.entity.Task;
import com.example.todoList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
  public ResponseEntity<Task> createTask(@RequestBody Task task) {
    Task newTask = taskService.addNewTask(task);
    newTask.add(linkTo(methodOn(TaskController.class)
      .createTask(task))
      .withSelfRel());
    return new ResponseEntity<>(task, HttpStatus.CREATED);
  }

  @DeleteMapping("/delete/{taskId}")
  public ResponseEntity<?> deleteTask(@PathVariable("taskId") Long taskId) {
    taskService.deleteTaskById(taskId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<Task> updateTask(@RequestBody Task task) {
    Task updatedTask = taskService.updateTask(task);
    updatedTask.add(linkTo(methodOn(TaskController.class)
      .updateTask(task))
      .withSelfRel());
    return new ResponseEntity<>(updatedTask, HttpStatus.OK);
  }

  @GetMapping("/all")
  public CollectionModel<Task> getAllTasks() {
    List<Task> allTasks = taskService.getAllTasks();
    for (Task task : allTasks) {
      Long taskId = task.getId();
      Link selfLink = linkTo(TaskController.class).slash(taskId).withSelfRel();
      task.add(selfLink);
    }
    Link link = linkTo(TaskController.class).withSelfRel();
    return CollectionModel.of(allTasks, link);
  }
}
