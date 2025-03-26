// PetRepository.java
package com.louis.springbootmongodb.repository;

import com.louis.springbootmongodb.entity.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetRepository extends MongoRepository<Pet, Integer> {
}