package com.example.springbootpractise.restTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsumingRestComponent implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestComponent.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        Quote quote = restTemplate.getForObject(
                "https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info("restTemplate print quote {}", quote.toString());
    }
}
