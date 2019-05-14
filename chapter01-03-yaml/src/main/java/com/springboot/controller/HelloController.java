package com.springboot.controller;

import com.springboot.entity.Louis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    Louis louis;

    @RequestMapping("/getYaml")
    public String helloWorld() {
        return louis.toString();
    }

}
