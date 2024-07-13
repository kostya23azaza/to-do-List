package com.example.todoList.service.mapper;

import com.example.todoList.dto.CatDto;
import com.example.todoList.entity.Cat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CatMapper {

    CatMapper INSTANCE = Mappers.getMapper(CatMapper.class);
    @Mapping(source = "race", target = "breed")
    CatDto catToCatDto(Cat cat);

    @Mapping(source = "breed", target = "race")
    Cat catDtoToCat(CatDto catDto);
}
