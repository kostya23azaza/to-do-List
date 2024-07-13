package com.example.todoList.controller;

import com.example.todoList.dto.CatDto;
import com.example.todoList.entity.Task;
import com.example.todoList.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cat", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatController {

    private final CatService catService;

    @GetMapping(value = "/{id}")
    public CatDto getById(@PathVariable("id") Long id) {
        return catService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CatDto dto) {
        catService.create(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        catService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody CatDto dto) {
        catService.update(id, dto);
    }

    @GetMapping("/all")
    public List<CatDto> getAll() {
        return catService.getAll();
    }
}
