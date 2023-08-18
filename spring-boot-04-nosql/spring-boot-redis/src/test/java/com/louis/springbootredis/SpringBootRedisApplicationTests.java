package com.louis.springbootredis;

import com.louis.springbootredis.entity.People;
import com.louis.springbootredis.util.RedisTemplateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootRedisApplicationTests {

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Test
    void contextLoads() {
        People people = new People();
        people.setName("Louis");
        people.setAge(18);
        redisTemplateUtil.set("people", people);
        redisTemplateUtil.set("people.f", people, 20);
        boolean exists = redisTemplateUtil.hasKey("people");
        if (exists) {
            System.out.println("exists is true");
            System.out.println(redisTemplateUtil.get("people"));
        } else {
            System.out.println("exists is false");
        }


    }


}
