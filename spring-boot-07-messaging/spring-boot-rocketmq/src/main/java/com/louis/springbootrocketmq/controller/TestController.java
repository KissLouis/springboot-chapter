package com.louis.springbootrocketmq.controller;

import com.louis.springbootrocketmq.dto.Order;
import com.louis.springbootrocketmq.producer.MessageProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TestController {
    private final MessageProducer messageProducer;

    public TestController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @GetMapping("/send")
    public String sendMessage() {
        messageProducer.sendSimpleMessage("Hello RocketMQ!");
        messageProducer.sendMessageWithTag("TagA Message");
        messageProducer.sendObjectMessage(new Order(1L, "20230520001", new BigDecimal("199.99")));
        return "Messages sent!";
    }
}
