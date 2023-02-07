package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GreetingController {
    private static final String TEMPLATE = "Wassup, %s";
    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world!") String name) throws SQLException {
        String curName = "";
        String sqlQuery = "select * from users where id = %d";
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(sqlQuery, counter.incrementAndGet()))) {

            resultSet.next();

            curName = resultSet.getString("name");


        }

        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, curName));
    }
}
