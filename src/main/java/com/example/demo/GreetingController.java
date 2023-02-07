package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GreetingController {
    private static final String TEMPLATE = "Wassup, %s";
    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world!!") String name) throws SQLException {
        String curName = "";
        String sqlQuery = "";
        if (!name.equals("world!!")) {
            sqlQuery = "INSERT INTO users(name, level, created_date) VALUES ('INS_NAME', 0, 'INS_DATE')";
            sqlQuery = sqlQuery.replaceAll("INS_NAME", name);
            sqlQuery = sqlQuery.replaceAll("INS_DATE", String.valueOf(LocalDate.now()));
        } else {
            sqlQuery = "select * from users where id = INS_ID";
            sqlQuery = sqlQuery.replaceAll("INS_ID", String.valueOf(counter.incrementAndGet()));
        }

        LocalDate localDate = LocalDate.now();
        int id = 0;
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/test", "vitos", "vitos");
            Statement statement = connection.createStatement()) {

            Boolean hasResults = statement.execute(sqlQuery);
            if (hasResults) {
                ResultSet resultSet = statement.getResultSet();
                resultSet.next();
                if (counter.get() == 4)
                    counter.set(0);

                curName = resultSet.getString("name");
                id = resultSet.getInt("id");
                localDate = resultSet.getObject(4, LocalDate.class);
            }

        }

        return new Greeting(id, String.format(TEMPLATE, curName), localDate);
    }
}
