package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @类描述：
 * @项目名称：chapter07-01-springsecurity-01-login
 * @包名： com.springboot.controller
 * @类名称：HomeController
 * @创建人：Louis
 * @创建时间：2019年6月26日下午3:57:46
 */
@Controller
public class HomeController {

    @GetMapping({"/", "/index", "/home"})
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
