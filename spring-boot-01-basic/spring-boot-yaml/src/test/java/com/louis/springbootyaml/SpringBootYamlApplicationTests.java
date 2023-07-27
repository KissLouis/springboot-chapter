package com.louis.springbootyaml;

import com.louis.springbootyaml.entity.People;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringBootYamlApplicationTests {

	@Autowired
	People people;



	@Test
	void contextLoads() {
		System.out.println(people.toString());
	}

}
