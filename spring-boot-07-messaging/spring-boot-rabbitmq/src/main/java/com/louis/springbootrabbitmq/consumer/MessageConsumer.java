package com.louis.springbootrabbitmq.consumer;

import com.louis.springbootrabbitmq.entity.People;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = "people_queue")
    @RabbitHandler
    public void handleMessage(People people) {
        System.out.println("Received message: " + people);
    }
}