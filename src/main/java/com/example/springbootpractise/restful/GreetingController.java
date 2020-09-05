package com.example.springbootpractise.restful;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GreetingController {

    //计数器
    private AtomicInteger counter = new AtomicInteger();

    private final String MESSAGE_TEMPLATE = "Hello, %s !";


    @RequestMapping("/sayHi")
    public GreetingEntity sayHi(@RequestParam(value = "user", defaultValue = "World") String user){

        return new GreetingEntity(counter.incrementAndGet(),String.format(MESSAGE_TEMPLATE,user));
    }
}
