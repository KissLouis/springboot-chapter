package com.louis.springbootredisannotation.service;

import com.louis.springbootredisannotation.entity.People;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PeopleService {

    // 模拟数据库存储
    private Map<String, People> PeopleMap = new HashMap<String, People>();

    public void savePeople(People People) {
        // 模拟数据库插入操作
        PeopleMap.put(People.getName(), People);
    }

    @Cacheable(value = "basePeopleInfo")
    public People getPeopleByName(String peopleName) {
        // 模拟数据库查询并返回
        return PeopleMap.get(peopleName);
    }

    @CachePut(value = "basePeopleInfo")
    public void updatePeopleAge(String peopleName, Integer age) {
        People people = PeopleMap.get(peopleName);
        people.setAge(age);
        // 模拟更新数据库
        PeopleMap.put(peopleName, people);
    }


}
