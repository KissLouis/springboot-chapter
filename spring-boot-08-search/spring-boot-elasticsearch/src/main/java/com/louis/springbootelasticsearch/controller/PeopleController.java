package com.louis.springbootelasticsearch.controller;


import com.louis.springbootelasticsearch.entity.People;
import com.louis.springbootelasticsearch.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PeopleController {
    @Autowired
    private PeopleRepository peopleRepository;

    @PostMapping("/people")
    public People createPeople(@RequestBody People people) {
        return peopleRepository.save(people);
    }

    @GetMapping("/people/{id}")
    public People getPeople(@PathVariable Integer id) {
        return peopleRepository.findById(id).orElse(null);
    }
}
