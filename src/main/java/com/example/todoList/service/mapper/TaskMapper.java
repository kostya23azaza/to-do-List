package com.example.todoList.service.mapper;

import com.example.todoList.dto.CatDto;
import com.example.todoList.dto.TaskDto;
import com.example.todoList.entity.Cat;
import com.example.todoList.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    @Mapping(source = "user.id", target = "userId")
    TaskDto taskToTaskDto(Task task);

    @Mapping(source = "userId", target = "user.id")
    Task taskDtoToTask(TaskDto taskDto);
}
