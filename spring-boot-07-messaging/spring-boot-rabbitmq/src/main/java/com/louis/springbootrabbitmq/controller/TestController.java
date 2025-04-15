package com.louis.springbootrabbitmq.controller;

import com.louis.springbootrabbitmq.entity.People;
import com.louis.springbootrabbitmq.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/send")
    public String sendMessage(@RequestBody People people) {
        messageProducer.sendPeopleMessage(people);
        return "Message sent!";
    }
}