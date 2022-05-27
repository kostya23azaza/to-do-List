package com.example.todoList.controller;

import com.example.todoList.entity.Task;
import com.example.todoList.service.TaskService;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

  private final TaskService taskService;

  @GetMapping(value = "/get/{taskId}")
  public Task getTaskById(@PathVariable("taskId") Long taskId) {
    return taskService.getTaskById(taskId);
  }

  @PostMapping("/create")
  public boolean createTask(@RequestBody Task task) {
    taskService.addNewTask(task);
    return true;
  }

  @DeleteMapping("/delete/{taskId}")
  public ResponseEntity<?> deleteTask(@PathVariable("taskId") Long taskId) {
    taskService.deleteTaskById(taskId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<Task> updateTask(@RequestBody Task task) {
    return new ResponseEntity<>(taskService.updateTask(task), HttpStatus.OK);
  }

  @GetMapping("/all")
  public List<Task> getAllTasks() {
    return taskService.getAllTasks();
  }
}
