package com.louis.springbootmybatisflex;

import com.louis.springbootmybatisflex.entity.People;
import com.louis.springbootmybatisflex.mapper.PeopleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootMybatisFlexApplicationTests {

    @Autowired
    private PeopleMapper peopleMapper;

    @Test
    void contextLoads() {
        updatePeople();
        for (People p : peopleMapper.selectAll()) {
            System.out.println(p.toString());
        }
    }


    public Integer addPeople() {
        People People = new People();
        People.setName("经理");
        People.setAge(18);
        return peopleMapper.insert(People);
    }


    public void updatePeople() {
        People People = new People();
        People.setName("update-man");
        People.setId(2);
        peopleMapper.update(People);
    }

    public List<People> getPeople() {
        return peopleMapper.selectAll();
    }


}
