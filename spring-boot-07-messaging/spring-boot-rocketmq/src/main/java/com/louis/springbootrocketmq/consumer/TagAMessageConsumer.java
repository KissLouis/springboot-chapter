package com.louis.springbootrocketmq.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = "demo-topic",
        consumerGroup = "${rocketmq.consumer.group}",
        selectorExpression = "tagA"  // 只消费tagA的消息
)
public class TagAMessageConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("Received TagA message: " + message);
    }
}

