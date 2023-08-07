package com.louis.springbootmybatisannotation;

import com.louis.springbootmybatisannotation.entity.People;
import com.louis.springbootmybatisannotation.mapper.PeopleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootMybatisAnnotationApplicationTests {


	@Autowired
	private PeopleMapper peopleMapper;

	@Test
	void contextLoads() {
		//addPeople();
		updatePeople();
		System.out.println(getPeople());
	}


	public Integer addPeople() {
		People People = new People();
		People.setName("经理");
		People.setAge(18);
		return peopleMapper.save(People);
	}


	public void updatePeople() {
		People People = new People();
		People.setName("update-man");
		People.setAge(28);
		People.setId(2);
		peopleMapper.update(People);
	}

	public List<People> getPeople() {
		return peopleMapper.findById(null);
	}


}
