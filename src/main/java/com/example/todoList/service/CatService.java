package com.example.todoList.service;

import com.example.todoList.dto.CatDto;
import com.example.todoList.entity.Cat;

import java.util.List;

public interface CatService {

    void create(CatDto dto);

    void update(Long id, CatDto dto);

    void delete(Long id);

    CatDto get(Long id);

    List<CatDto> getAll();
}
