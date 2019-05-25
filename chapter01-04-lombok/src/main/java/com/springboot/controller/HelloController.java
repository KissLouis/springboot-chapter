package com.springboot.controller;

import com.springboot.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    People people;

    @RequestMapping("/getYaml")
    public String helloWorld() {
        return people.toString();
    }

}
