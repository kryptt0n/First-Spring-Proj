package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class TransactionController {

    @GetMapping("/transaction")
    public void transact() {

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/test", "vitos", "vitos");
            Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();

            statement.executeUpdate("UPDATE users SET level = 10 WHERE id = 5");

            statement.executeUpdate("UPDATE users SET level = 10 WHERE id = 7");

//            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");
//            resultSet.next();
//            System.out.println(resultSet.getInt(1));
//
//            resultSet = statement.executeQuery("SELECT COUNT(*) FROM tasks");
//            resultSet.next();
//            System.out.println(resultSet.getInt(1));



            try {
//                resultSet = statement.executeQuery("SELECT COUNT() FROM tasks");
                statement.executeUpdate("UPDATE userss SET level = 10 WHERE id = 5");

            } catch (SQLException e) {
                connection.rollback(savepoint);
            }


        } catch (SQLException ignored) {

        }

    }
}
