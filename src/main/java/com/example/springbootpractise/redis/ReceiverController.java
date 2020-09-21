package com.example.springbootpractise.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ReceiverController{

    private static final Logger log = LoggerFactory.getLogger(ReceiverController.class);

    @Autowired
    private Receiver receiver;

    @Autowired
    private StringRedisTemplate template ;


    @RequestMapping("/redis/test")
    public String test() throws InterruptedException {
        while (receiver.getCount() == 0) {
            log.info("Sending message...");
            template.convertAndSend("chat", "Hello from Redis!");
            Thread.sleep(500L);
        }
        return "success";
    }

}
