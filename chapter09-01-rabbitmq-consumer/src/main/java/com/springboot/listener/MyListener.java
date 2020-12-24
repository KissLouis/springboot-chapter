package com.springboot.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class MyListener {


    /**
     * 接收队列消息
     *
     * @param message
     */
    @RabbitListener(queues = "item_queue")
    public void MyListener1(String message) {
        System.out.println("消费者接收到消息" + message);
    }

}
