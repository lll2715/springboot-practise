package com.example.springbootpractise.mq;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MqReceiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void consumeMsg(String message){
        System.out.println("Reveived <"+message+">");
        latch.countDown();
    }

    public CountDownLatch getLatch(){
        return latch;
    }
}
