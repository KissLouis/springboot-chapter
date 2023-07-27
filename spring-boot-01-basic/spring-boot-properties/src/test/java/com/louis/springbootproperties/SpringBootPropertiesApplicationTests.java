package com.louis.springbootproperties;

import com.louis.springbootproperties.entity.Properties1;
import com.louis.springbootproperties.entity.Properties2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class SpringBootPropertiesApplicationTests {


	@Autowired
	Properties1 properties1;

	@Autowired
	Properties2 properties2;


	@Autowired
	Environment environment;

	@Test
	void contextLoads() {
		System.out.println(properties1.toString());
		System.out.println(properties2.toString());
		System.out.println("name=" + environment.getProperty("com.louis.name") + ",username=" + environment.getProperty("com.louis.user[username]"));
	}

}
