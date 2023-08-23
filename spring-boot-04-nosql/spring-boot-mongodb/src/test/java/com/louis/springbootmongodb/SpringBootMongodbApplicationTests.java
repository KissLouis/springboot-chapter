package com.louis.springbootmongodb;

import com.louis.springbootmongodb.entity.People;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

@SpringBootTest
class SpringBootMongodbApplicationTests {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        System.out.println(findAndModifyPeople());
        removePeople();
    }


    public void addPeople() {
        People people = new People();
        people.setId(2);
        people.setName("Louis");
        people.setAge(18);
        mongoTemplate.insert(people);
    }


    public People selectPeople() {
        Query queryOne = new Query(Criteria.where("id").is(1));
        People one = mongoTemplate.findOne(queryOne, People.class);
        return one;
    }


    public List<People> selectPeopleList() {
        //查询List
        Query queryList = new Query(Criteria.where("name").is("Louis").and("age").is(18));
        List<People> userList = mongoTemplate.find(queryList, People.class);
        return userList;
    }


    public People findAndModifyPeople() {
        Query query = new Query(Criteria.where("id").is(2));
        Update update = new Update();
        update.set("age", 25);
        FindAndModifyOptions options = new FindAndModifyOptions();
        // 先查询，如果没有符合条件的，会执行插入，插入的值是查询值 ＋ 更新值
        options.upsert(true);
        // 返回当前最新值
        options.returnNew(true);
        return mongoTemplate.findAndModify(query, update, options, People.class);
    }


    public void removePeople() {
        Query query = new Query(Criteria.where("id").is("2"));
        mongoTemplate.remove(query, People.class);
    }


}
