package com.louis.springbootmybatis;

import com.louis.springbootmybatis.entity.People;
import com.louis.springbootmybatis.entity.Pet;
import com.louis.springbootmybatis.mapper.PeopleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootMybatisApplicationTests {


	@Autowired
	private PeopleMapper peopleMapper;

	@Test
	void contextLoads() {
		addPeople();
		System.out.println(getPeople());
	}


	public void addPeople() {
		People People = new People();
		People.setName("经理");
		People.setAge(18);
		peopleMapper.save(People);
	}

	public List<People> getPeople() {
		return peopleMapper.findById(null);
	}


}
