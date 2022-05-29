package com.example.todoList.controller;

import com.example.todoList.entity.Task;
import com.example.todoList.entity.User;
import com.example.todoList.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

  @InjectMocks
  private TaskController taskController;

  @Mock
  private TaskService taskService;

  private Task task;

  @BeforeEach
  public void setUp() {
    task = new Task();
    task.setId(1L);
    task.setName("test task");
    task.setUser(mock(User.class));
  }

  @Test
  public void getTaskByIdTest() {
    when(taskService.getTaskById(1L)).thenReturn(task);
    assertEquals(task, taskController.getTaskById(1L));
    verify(taskService).getTaskById(1L);
  }

  @Test
  public void createTaskTest() {
    when(taskService.create(task)).thenReturn(task);
    assertEquals(task, taskController.createTask(task));
    verify(taskService).create(task);
  }

  @Test
  public void deleteTaskByIdTest() {
    taskController.deleteTask(1L);
    verify(taskService).deleteTaskById(1L);
  }

  @Test
  public void updateTaskTest() {
    when(taskService.update(task)).thenReturn(task);
    assertEquals(task, taskController.updateTask(task));
    verify(taskService).update(task);
  }

  @Test
  public void getAllTasksTest() {
    when(taskService.getAll()).thenReturn(List.of(task));
    assertEquals(new ArrayList<>(taskController.getAllTasks().getContent()), List.of(task));
    verify(taskService).getAll();
  }

}
