package com.louis.springbootfreemarker;

import com.louis.springbootfreemarker.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Louis
 */
@SpringBootApplication
@Controller
public class SpringBootFreemarkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFreemarkerApplication.class, args);
	}

	@Autowired
	People people;


	@RequestMapping("/index1")
	public String index1(Model model) {
		model.addAttribute("name", "Model方式");
		model.addAttribute("people", people);
		return "index";
	}

	@RequestMapping("/index2")
	public String index2(ModelMap modelMap) {
		modelMap.addAttribute("name", "ModelMap方式");
		modelMap.addAttribute("people", people);
		return "index";
	}

	@RequestMapping("/index3")
	public ModelAndView index3(ModelAndView modelAndView) {
		modelAndView.addObject("name", "ModelAndView方式");
		modelAndView.addObject("people", people);
		modelAndView.setViewName("index");
		return modelAndView;
	}
}
