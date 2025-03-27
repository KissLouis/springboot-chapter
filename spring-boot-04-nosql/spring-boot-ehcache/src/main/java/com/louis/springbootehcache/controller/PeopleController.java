package com.louis.springbootehcache.controller;

import com.louis.springbootehcache.entity.People;
import com.louis.springbootehcache.entity.Pet;
import com.louis.springbootehcache.service.PeopleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping("/people")
    public People addPeople(@RequestBody People people) {
        return peopleService.addPeople(people);
    }

    @GetMapping("/people/{id}")
    public People getPeople(@PathVariable Integer id) {
        return peopleService.getPeopleById(id);
    }

    @PutMapping("/people")
    public People updatePeople(@RequestBody People people) {
        return peopleService.updatePeople(people);
    }

    @DeleteMapping("/people/{id}")
    public void deletePeople(@PathVariable Integer id) {
        peopleService.deletePeople(id);
    }

    @PostMapping("/pets")
    public Pet addPet(@RequestBody Pet pet) {
        return peopleService.addPet(pet);
    }

    @GetMapping("/pets/{id}")
    public Pet getPet(@PathVariable Integer id) {
        return peopleService.getPetById(id);
    }
}