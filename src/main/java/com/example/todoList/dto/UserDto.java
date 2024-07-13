package com.example.todoList.dto;

import com.example.todoList.entity.Role;
import com.example.todoList.entity.Task;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto extends RepresentationModel<UserDto> {
    Long id;
    String username;
    String email;
    Set<Role> role;
    List<Task> tasks;
}
