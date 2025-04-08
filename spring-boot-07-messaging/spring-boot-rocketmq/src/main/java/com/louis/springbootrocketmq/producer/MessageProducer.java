package com.louis.springbootrocketmq.producer;

import com.louis.springbootrocketmq.dto.Order;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送简单消息
     */
    public void sendSimpleMessage(String message) {
        rocketMQTemplate.convertAndSend("demo-topic", message);
    }

    /**
     * 发送带tag的消息
     */
    public void sendMessageWithTag(String message) {
        rocketMQTemplate.convertAndSend("demo-topic:tagA", message);
    }

    /**
     * 发送对象消息
     */
    public void sendObjectMessage(Order order) {
        rocketMQTemplate.convertAndSend("order-topic", order);
    }
}

