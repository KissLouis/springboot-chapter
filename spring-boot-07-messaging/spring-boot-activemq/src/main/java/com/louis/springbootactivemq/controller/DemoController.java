package com.louis.springbootactivemq.controller;

import com.louis.springbootactivemq.component.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final MessageProducer producer;

    @GetMapping("/send")
    public String send(@RequestParam String msg) {
        producer.sendMessage(msg);
        return "Sent: " + msg;
    }
}