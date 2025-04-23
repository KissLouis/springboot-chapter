// PeopleRepository.java
package com.louis.springbootelasticsearch.repository;

import com.louis.springbootelasticsearch.entity.People;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PeopleRepository extends ElasticsearchRepository<People, Integer> {
}