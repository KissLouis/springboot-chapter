package com.louis.springbootrocketmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class MessageConverterConfig {

    @Bean
    public MessageConverter jacksonMessageConverter() {
        return new MappingJackson2MessageConverter();
    }
}
