package com.louis.springbootrabbitmq.producer;

import com.louis.springbootrabbitmq.entity.People;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendPeopleMessage(People people) {
        rabbitTemplate.convertAndSend(
                "people_exchange",
                "people_routingKey",
                people
        );
    }
}