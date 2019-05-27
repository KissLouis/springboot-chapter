package com.springboot.service;

import com.springboot.entity.People;
import com.springboot.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Louis
 * @title: PeopleService
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/27 16:22
 */
@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public List findAll() {
        return peopleRepository.findAll();
    }

    public void delete(Integer integer) {
        peopleRepository.deleteById(integer);
    }

    public People flush(People people) {
        return peopleRepository.saveAndFlush(people);
    }

    public People save(People people) {
        return peopleRepository.save(people);
    }

    public Optional<People> findById(Integer id) {
        return peopleRepository.findById(id);
    }


}
