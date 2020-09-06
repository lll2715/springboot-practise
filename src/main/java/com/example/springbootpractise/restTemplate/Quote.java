package com.example.springbootpractise.restTemplate;

import lombok.Data;

@Data
public class Quote {

    private String type;

    private Value value;

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
