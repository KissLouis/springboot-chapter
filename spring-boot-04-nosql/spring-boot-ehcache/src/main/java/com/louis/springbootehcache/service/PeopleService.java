package com.louis.springbootehcache.service;

import com.louis.springbootehcache.entity.People;
import com.louis.springbootehcache.entity.Pet;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@CacheConfig(cacheNames = "peopleCache")
public class PeopleService {

    // 模拟数据库存储
    private final Map<Integer, People> peopleDB = new HashMap<>();
    private final Map<Integer, Pet> petDB = new HashMap<>();

    // 添加Person
    @CachePut(key = "#people.id")
    public People addPeople(People people) {
        peopleDB.put(people.getId(), people);
        return people;
    }

    // 查询Person
    @Cacheable(key = "#id")
    public People getPeopleById(Integer id) {
        simulateSlowQuery(); // 模拟耗时操作
        return peopleDB.get(id);
    }

    // 更新Person
    @CachePut(key = "#people.id")
    public People updatePeople(People people) {
        peopleDB.put(people.getId(), people);
        return people;
    }

    // 删除Person
    @CacheEvict(key = "#id")
    public void deletePeople(Integer id) {
        peopleDB.remove(id);
    }

    // 添加Pet
    @Cacheable(cacheNames = "petCache", key = "#pet.id")
    public Pet addPet(Pet pet) {
        petDB.put(pet.getId(), pet);
        return pet;
    }

    // 查询Pet
    @Cacheable(cacheNames = "petCache", key = "#id")
    public Pet getPetById(Integer id) {
        return petDB.get(id);
    }

    // 模拟数据库查询延迟
    private void simulateSlowQuery() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}