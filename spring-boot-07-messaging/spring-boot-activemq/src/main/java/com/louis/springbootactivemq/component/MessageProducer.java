package com.louis.springbootactivemq.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProducer {
    private final JmsTemplate jmsTemplate;

    @Value("${demo.queue}")
    private String queue;

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend(queue, message);
        System.out.println("Sent: " + message);
    }
}