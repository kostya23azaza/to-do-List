package com.example.todoList.controller;

import com.example.todoList.dto.TaskDto;
import com.example.todoList.entity.Task;
import com.example.todoList.entity.User;
import com.example.todoList.exception.UserNotFoundException;
import com.example.todoList.repository.UserRepository;
import com.example.todoList.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

  @InjectMocks
  private TaskController taskController;

  @MockBean
  private MockMvc mockMvc;

  @Mock
  private TaskService taskService;

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
    when(taskService.getById(1L)).thenReturn(task);
    assertEquals(task, taskController.getTaskById(1L));
    verify(taskService).getById(1L);
  }

//  @Test
//  public void testCreate_NonExistingUser() {
//    TaskDto taskDto = new TaskDto();
//    taskDto.setName("Test Task");
//    taskDto.setUserId(22L); //  Non-existing user ID
//
//    // Заглушки для репозиториев
//    when(userRepository.existsById(2L)).thenReturn(false);
//
//    // Проверка исключения
//    UserNotFoundException exception = assertThrows(UserNotFoundException.class,
//            () -> taskService.create(taskDto));
//
//    // Проверка сообщения об ошибке
//    assertThat(exception.getMessage()).isEqualTo("user with id 2 doesn't not exist");
//  }

  @Test
  public void deleteTaskByIdTest() {
    taskController.delete(1L);
    verify(taskService).deleteById(1L);
  }

  @Test
  public void updateTaskTest() {
    when(taskService.update(task)).thenReturn(task);
    assertEquals(task, taskController.update(task));
    verify(taskService).update(task);
  }

//  @Test
//  public void getAllTasksTest() {
//    List<Task> tasks = new ArrayList<>();
//    Task task1 = new Task();
//    task1.setId(1L);
//    task1.setName("Task 1");
//    tasks.add(task1);
//    when(taskService.getAll()).thenReturn(tasks);
//    verify(taskService).getAll();
//  }

}
