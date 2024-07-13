package com.example.todoList.service;

import com.example.todoList.dto.CatDto;
import com.example.todoList.entity.Cat;
import com.example.todoList.exception.CatNotFoundException;
import com.example.todoList.repository.CatRepository;
import com.example.todoList.service.mapper.CatMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    private final CatMapper catMapper;

    public CatServiceImpl(CatRepository catRepository, CatMapper catMapper) {
        this.catRepository = catRepository;
        this.catMapper = catMapper;
    }

    @Override
    public void create(CatDto dto) {
        catRepository.save(catMapper.catDtoToCat(dto));
    }

    @Override
    public void update(Long id, CatDto dto) {
        Cat cat = catRepository.findById(id).orElseThrow(() -> new CatNotFoundException("Не найден кот с id = " + id));
        cat.setColor(dto.getColor());
        cat.setName(dto.getName());
        catRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        catRepository.deleteById(id);
    }

    @Override
    public CatDto get(Long id) {
        Cat cat = catRepository.findById(id).orElseThrow(() -> new CatNotFoundException("Не найден кот с id = " + id));
        return catMapper.catToCatDto(cat);
    }

    @Override
    public List<CatDto> getAll() {
        return catRepository.findAll().stream().map(catMapper::catToCatDto).toList();
    }
}
