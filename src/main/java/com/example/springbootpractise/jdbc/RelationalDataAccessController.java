package com.example.springbootpractise.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RelationalDataAccessController {

    private static final Logger log = LoggerFactory.getLogger(RelationalDataAccessController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/relation_data_operate")
    public String dbOperate(){

        log.info("db operate start");

        jdbcTemplate.execute("DROP TABLE IF EXISTS `customer`");

        jdbcTemplate.execute("CREATE TABLE customer(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        List<Object[]> splitNames = Arrays.asList("Josh Jack", "Ha Mark").stream().map(name -> name.split(" "))
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate("insert into `customer`(`first_name`,`last_name`) values (?,?) ",splitNames);

        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customer WHERE id = 2",
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> log.info(customer.toString()));


        return "success";
    }
}
