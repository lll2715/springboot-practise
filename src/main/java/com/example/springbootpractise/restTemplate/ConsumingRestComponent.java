package com.example.springbootpractise.restTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsumingRestComponent{

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestComponent.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/rest/test")
    public String run(String... args) throws Exception {
        Quote quote = restTemplate.getForObject(
                "https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info("restTemplate print quote {}", quote.toString());
        return "success";
    }
}
