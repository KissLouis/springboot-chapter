package com.louis.springbootredisannotation;

import com.louis.springbootredisannotation.entity.People;
import com.louis.springbootredisannotation.service.PeopleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootRedisAnnotationApplicationTests {


    @Autowired
    private PeopleService peopleService;

    @Test
    void contextLoads() {
        People people = new People();
        people.setName("Louis");
        people.setAge(18);
        // 向 redis 中存入数据
        peopleService.savePeople(people);

        // 从 redis 中取数据
        People peopleInfo = peopleService.getPeopleByName(people.getName());
        System.out.println("查询：" + peopleInfo);

        // 更新 people 的描述信息后查询
        peopleService.updatePeopleAge(people.getName(), 20);
        peopleInfo = peopleService.getPeopleByName(people.getName());
        System.out.println("更新后查询：" + peopleInfo);

    }

}
