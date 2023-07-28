package com.louis.springbootthymeleaf;

import com.louis.springbootthymeleaf.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class SpringBootThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootThymeleafApplication.class, args);
	}


	@Autowired
	People people;


	@GetMapping("/index")
	public String hello(Model model) {
		model.addAttribute("name", "name");
		model.addAttribute("people", people.toString());
		model.addAttribute("peopleType", people.getType());
		return "index";
	}
}
