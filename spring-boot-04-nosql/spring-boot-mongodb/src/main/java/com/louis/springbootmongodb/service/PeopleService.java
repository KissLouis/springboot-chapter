// PeopleService.java
package com.louis.springbootmongodb.service;

import com.louis.springbootmongodb.entity.People;
import com.louis.springbootmongodb.entity.Pet;
import com.louis.springbootmongodb.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PetService petService;

    // 创建用户并处理宠物关联
    public People create(People people) {
        People savedPeople = peopleRepository.save(people);
        if (savedPeople.getPetList() != null) {
            savedPeople.getPetList().forEach(pet -> {
                pet.setPeople(savedPeople);
                petService.savePet(pet);
            });
        }
        return savedPeople;
    }

    // 更新用户时同步更新宠物
    public Optional<People> update(Integer id, People updatedPeople) {
        return peopleRepository.findById(id).map(existing -> {
            existing.setName(updatedPeople.getName());
            existing.setAge(updatedPeople.getAge());

            // 清除旧宠物关联
            existing.getPetList().forEach(pet -> pet.setPeople(null));
            petService.deleteAllByPeople(existing);

            // 设置新宠物关联
            if (updatedPeople.getPetList() != null) {
                updatedPeople.getPetList().forEach(pet -> {
                    pet.setPeople(existing);
                    petService.savePet(pet);
                });
                existing.setPetList(updatedPeople.getPetList());
            }
            return peopleRepository.save(existing);
        });
    }

    // 其他基础方法
    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    public Optional<People> findById(Integer id) {
        return peopleRepository.findById(id);
    }

    public void delete(Integer id) {
        peopleRepository.findById(id).ifPresent(people -> {
            petService.deleteAllByPeople(people);
            peopleRepository.deleteById(id);
        });
    }
}