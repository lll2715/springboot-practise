package com.example.springbootpractise.restful;

import lombok.Data;

@Data
public class GreetingEntity {

    private Integer id;

    private String msg;

    public GreetingEntity(Integer id, String msg) {
        this.id = id;
        this.msg = msg;
    }
}
