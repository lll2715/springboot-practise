package com.example.springbootpractise.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Receiver {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    private AtomicInteger counter = new AtomicInteger();

    public void receiveMessage(String message){
        log.info("receive :" + message);
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }
}
