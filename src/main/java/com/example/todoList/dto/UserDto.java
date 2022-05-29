package com.example.todoList.dto;

import com.example.todoList.entity.Role;
import com.example.todoList.entity.Task;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserDto extends RepresentationModel<UserDto> {
  private Long id;
  private String username;
  private String email;
  private Set<Role> role;
  private List<Task> tasks;
}
