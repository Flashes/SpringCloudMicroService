package com.imooc.order.repository;

import com.imooc.order.OrderApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MqSenderTest extends OrderApplicationTests{
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Test
    public void send(){
        amqpTemplate.convertAndSend("myQueue","myReceive");
    }
}
