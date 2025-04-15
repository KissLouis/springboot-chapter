package com.louis.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public Queue peopleQueue() {
        return new Queue("people_queue", true); // 持久化队列
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("people_exchange");
    }

    @Bean
    public Binding binding(Queue peopleQueue, DirectExchange exchange) {
        return BindingBuilder.bind(peopleQueue).to(exchange).with("people_routingKey");
    }
}