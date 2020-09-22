package com.example.springbootpractise.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MqReceiver mqReceiver;

    @RequestMapping("mq/test")
    public String test() throws InterruptedException {
        System.out.println("Sending message");
        rabbitTemplate.convertAndSend(MqConstant.topicExchangeName,"foo.bar.baz", "Hello from RabbitMQ!");
        mqReceiver.getLatch().await(5, TimeUnit.SECONDS);
        return "success";
    }

}
