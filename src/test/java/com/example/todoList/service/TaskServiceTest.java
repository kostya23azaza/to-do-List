package com.example.todoList.service;

import com.example.todoList.entity.Task;
import com.example.todoList.entity.User;
import com.example.todoList.exception.TaskNotFoundException;
import com.example.todoList.exception.UserNotFoundException;
import com.example.todoList.repository.TaskRepository;
import com.example.todoList.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

  @InjectMocks
  private TaskService taskService;
  @Mock
  private TaskRepository taskRepository;
  @Mock
  private UserRepository userRepository;
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
    when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
    Task taskById = taskService.getTaskById(1L);
    assertEquals(task, taskById);
    verify(taskRepository).findById(1L);
  }

  @Test
  public void getTaskByIdWhenTaskNotFoundExceptionWasThrown() {
    when(taskRepository.findById(3L)).thenReturn(Optional.empty());
    assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(3L));
    verify(taskRepository).findById(3L);
  }

  @Test
  public void deleteTaskByIdTest() {
    when(taskRepository.existsById(3L)).thenReturn(true);
    taskService.deleteTaskById(3L);
    verify(taskRepository).deleteById(3L);
  }

  @Test
  public void deleteTaskByIdWhenTaskNotFoundExceptionWasThrown() {
    when(taskRepository.existsById(3L)).thenReturn(false);
    assertThrows(TaskNotFoundException.class, () -> taskService.deleteTaskById(3L));
    verify(taskRepository).existsById(3L);
  }

  @Test
  public void getAllTasksTest() {
    taskService.getAll();
    verify(taskRepository).findAll();
  }

  @Test
  public void updateTaskTest() {
    when(taskRepository.existsById(1L)).thenReturn(true);
    when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

    Task newTask = new Task();
    newTask.setId(1L);
    newTask.setName("new task");
    Task updatedTask = taskService.update(newTask);

    assertEquals(newTask, updatedTask);

    verify(taskRepository).existsById(1L);
    verify(taskRepository).findById(1L);
  }

  @Test
  public void updateTaskTestWhenTaskNotFoundExceptionWasThrown() {
    when(taskRepository.existsById(3L)).thenReturn(false);

    Task failTask = new Task();
    failTask.setId(3L);

    assertThrows(TaskNotFoundException.class, () -> taskService.update(failTask));
  }

  @Test
  public void addNewTaskTest() {
    User testUser = new User();
    testUser.setId(2L);

    Task newTask = new Task();
    newTask.setName("new task");
    newTask.setUser(testUser);
    newTask.setId(5L);

    when(userRepository.existsById(2L)).thenReturn(true);
    assertEquals(taskService.create(newTask), newTask);

    verify(userRepository).existsById(2L);
    verify(taskRepository).save(newTask);
  }

  @Test
  public void addNewUserWhenUserNotFoundExceptionWasThrown() {
    User testUser = new User();
    testUser.setId(2L);

    Task newTask = new Task();
    newTask.setName("new task");
    newTask.setUser(testUser);
    newTask.setId(5L);

    when(userRepository.existsById(2L)).thenReturn(false);
    assertThrows(UserNotFoundException.class, () -> taskService.create(newTask));
  }
}
