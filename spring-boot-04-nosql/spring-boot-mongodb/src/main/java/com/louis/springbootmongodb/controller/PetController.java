// PetController.java
package com.louis.springbootmongodb.controller;

import com.louis.springbootmongodb.entity.Pet;
import com.louis.springbootmongodb.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet create(@RequestBody Pet pet) {
        return petService.savePet(pet);
    }

    @GetMapping
    public List<Pet> getAll() {
        return petService.findAll();
    }

    @GetMapping("/{id}")
    public Pet getById(@PathVariable Integer id) {
        return petService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        petService.delete(id);
    }
}