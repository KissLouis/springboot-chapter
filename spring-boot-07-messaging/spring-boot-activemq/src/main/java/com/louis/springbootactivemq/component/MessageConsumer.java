package com.louis.springbootactivemq.component;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @JmsListener(destination = "${demo.queue}")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }
}