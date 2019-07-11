package com.springboot.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.util.i18n.LocaleMessage;

@Controller
public class IndexController {

	@Resource
	private LocaleMessage LocaleMessage;

	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}

}
