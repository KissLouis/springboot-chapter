// PetService.java
package com.louis.springbootmongodb.service;

import com.louis.springbootmongodb.entity.People;
import com.louis.springbootmongodb.entity.Pet;
import com.louis.springbootmongodb.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Optional<Pet> findById(Integer id) {
        return petRepository.findById(id);
    }

    public void delete(Integer id) {
        petRepository.deleteById(id);
    }

    public void deleteAllByPeople(People people) {
        petRepository.deleteAll(people.getPetList());
    }
}