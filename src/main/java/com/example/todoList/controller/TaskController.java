package com.example.todoList.controller;

import com.example.todoList.dto.TaskDto;
import com.example.todoList.entity.Task;
import com.example.todoList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/tasks")
public class TaskController {

  private final TaskService taskService;

  @GetMapping(value = "/get/{taskId}")
  public Task getTaskById(@PathVariable("taskId") Long taskId) {
    Task task = taskService.getById(taskId);
    task.add(linkTo(methodOn(TaskController.class)
      .getTaskById(taskId))
      .withSelfRel());
    return task;
  }

  @PostMapping("/create")
  public String create(@RequestBody TaskDto taskDto, Model model) {
    Task newTask = taskService.create(taskDto);
    model.addAttribute("task", newTask);
    return "create";
  }

  @DeleteMapping("/delete/{taskId}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("taskId") Long taskId) {
    taskService.deleteById(taskId);
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
  public String getAll(Model model) {
    List<Task> allTasks = taskService.getAll();
    model.addAttribute("tasks", allTasks);
    return "tasks";
  }
}
