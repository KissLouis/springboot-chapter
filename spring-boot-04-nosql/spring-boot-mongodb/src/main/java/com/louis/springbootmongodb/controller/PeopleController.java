// PeopleController.java
package com.louis.springbootmongodb.controller;

import com.louis.springbootmongodb.entity.People;
import com.louis.springbootmongodb.service.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public People create(@RequestBody People people) {
        return peopleService.create(people);
    }

    @GetMapping
    public List<People> getAll() {
        return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public People getById(@PathVariable Integer id) {
        return peopleService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public People update(@PathVariable Integer id, @RequestBody People people) {
        return peopleService.update(id, people)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        peopleService.delete(id);
    }
}