package com.louis.springbootmybatisflex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.louis.springbootmybatisflex.mapper")
public class SpringBootMybatisFlexApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisFlexApplication.class, args);
	}

}
