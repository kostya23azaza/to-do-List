package com.example.todoList.service;


import com.example.todoList.entity.Task;
import com.example.todoList.exception.TaskNotFoundException;
import com.example.todoList.exception.UserNotFoundException;
import com.example.todoList.repository.TaskRepository;
import com.example.todoList.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;
  private final UserRepository userRepository;

  public Task getTaskById(Long id) {
    return taskRepository.findById(id)
      .orElseThrow(() -> new TaskNotFoundException(String.format("task with id %s doesn't not exist", id)));
  }

  public void deleteTaskById(Long id) {
    if (taskRepository.existsById(id)) {
      taskRepository.deleteById(id);
    } else {
      throw new TaskNotFoundException(String.format("task with id %s doesn't not exist", id));
    }
  }

  public Task update(Task task) {
    Long id = Optional.of(task).orElseThrow(() -> new IllegalArgumentException("task id can't be null")).getId();
    if (taskRepository.existsById(id)) {
      Task updatedTask = taskRepository.findById(id)
        .orElseThrow(() -> new TaskNotFoundException(String.format("task with id %s doesn't not exist", id)));
    updatedTask.setName(task.getName());
    taskRepository.save(updatedTask);
    } else {
      throw new TaskNotFoundException(String.format("task with id %s doesn't not exist", id));
    }
    return task;
  }

  public Task create(Task task) {
    Long userId = Optional.of(task.getUser())
      .orElseThrow(() -> new UserNotFoundException("user with doesn't not exist")).getId();
    if (userRepository.existsById(userId)) {
      taskRepository.save(task);
      return task;
    }
    throw new UserNotFoundException(String.format("user with id %s doesn't not exist", userId));
  }

  public List<Task> getAll() {
    return taskRepository.findAll();
  }
}
