package com.louis.springbootrocketmq.consumer;

import com.louis.springbootrocketmq.dto.Order;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = "order-topic",
        consumerGroup = "${rocketmq.consumer.group}"
)
public class OrderMessageConsumer implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        System.out.println("Received order: " + order);
    }
}
