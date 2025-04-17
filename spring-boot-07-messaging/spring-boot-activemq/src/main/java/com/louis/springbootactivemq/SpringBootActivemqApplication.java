package com.louis.springbootactivemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringBootActivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActivemqApplication.class, args);
	}

}
