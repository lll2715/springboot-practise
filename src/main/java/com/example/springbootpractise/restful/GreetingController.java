package com.example.springbootpractise.restful;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GreetingController {

    //计数器
    private AtomicInteger counter = new AtomicInteger();

    private final String MESSAGE_TEMPLATE = "Hello, %s !";


    @RequestMapping("/sayHi")
    public GreetingEntity sayHi(@RequestParam(value = "user", defaultValue = "World") String user){
        System.gc();
        return new GreetingEntity(counter.incrementAndGet(),String.format(MESSAGE_TEMPLATE,user));
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/greeting")
    public GreetingEntity greeting(@RequestParam(required = false, defaultValue = "World") String name) {
        System.out.println("==== get greeting ====");
        return new GreetingEntity(counter.incrementAndGet(), String.format(MESSAGE_TEMPLATE, name));
    }


    @GetMapping("/greeting-javaconfig")
    public GreetingEntity greetingWithJavaconfig(@RequestParam(required = false, defaultValue = "World") String name) {
        System.out.println("==== in greeting ====");
        return new GreetingEntity(counter.incrementAndGet(), String.format(MESSAGE_TEMPLATE, name));
    }
}
