package com.louis.springbootmybatisplus;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.louis.springbootmybatisplus.entity.People;
import com.louis.springbootmybatisplus.mapper.PeopleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootMybatisPlusApplicationTests {

    @Autowired
    private PeopleMapper peopleMapper;

    @Test
    void contextLoads() {
        for (People p : peopleMapper.selectList(null)) {
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
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", 2);
        peopleMapper.update(People, updateWrapper);
    }

    public List<People> getPeople() {
        return peopleMapper.selectList(null);
    }

}
