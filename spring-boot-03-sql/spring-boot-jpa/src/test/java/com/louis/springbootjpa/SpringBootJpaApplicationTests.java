package com.louis.springbootjpa;

import com.louis.springbootjpa.entity.People;
import com.louis.springbootjpa.entity.Pet;
import com.louis.springbootjpa.repository.PeopleRepository;
import com.louis.springbootjpa.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootJpaApplicationTests {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PetRepository petRepository;


    @Test
    void contextLoads() {
        for (People p : peopleRepository.findAll()) {
            System.out.println(p.toString());
        }
    }

    public void addPeople() {
        People People = new People();
        People.setName("经理");
        People.setAge(18);
        peopleRepository.save(People);
    }

    public void addPet() {
        Pet Pet = new Pet();
        Pet.setPetName("旺旺");
        Pet.setPeople(peopleRepository.getOne(1));
        petRepository.save(Pet);
    }

    public List<People> getPeople() {
        return peopleRepository.findAll();
    }

}
