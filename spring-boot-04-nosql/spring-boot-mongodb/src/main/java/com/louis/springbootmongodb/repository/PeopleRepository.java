// PeopleRepository.java
package com.louis.springbootmongodb.repository;

import com.louis.springbootmongodb.entity.People;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PeopleRepository extends MongoRepository<People, Integer> {
}