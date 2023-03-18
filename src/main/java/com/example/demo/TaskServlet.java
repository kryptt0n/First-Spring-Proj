package com.example.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;

@RestController
public class TaskServlet extends HttpServlet {
    @Override
    @GetMapping("/tasksServlet")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        LocalDate localDate = LocalDate.now();
        String user_id = request.getParameter("userId");
        PrintWriter pw = response.getWriter();

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/test", "vitos", "vitos")) {

            if (name != null && user_id != null) {
                String sqlQuery = "INSERT INTO tasks(name, register_date, user_id) VALUES (?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setString(1, name);
                preparedStatement.setDate(2, new Date(LocalDate.now().toEpochDay()));
                preparedStatement.setInt(3, Integer.valueOf(user_id));
                preparedStatement.executeUpdate();
                pw.println("<html>");
                pw.println("<h1> Hello, friend! Your tasks has been added");
                pw.println("</h1>");
                pw.println("<p>" + "to " + name + " for " + user_id + "</p>");
                pw.println("</html>");
            } else if (user_id != null) {
                String sqlQuery = "SELECT tasks.id, tasks.name, register_date, u.name FROM tasks JOIN users u on u.id = tasks.user_id WHERE user_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, Integer.valueOf(user_id));
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                pw.println("<html>");
                pw.println("<h1> Hello, friend! Here are some tasks for " + resultSet.getString(4) + "</h1>");
                do {
                    pw.println("<p>");
                    pw.println(resultSet.getString(2) + " from " + resultSet.getDate(3) + " for "
                            + resultSet.getString(4));
                    pw.println("</p>");
                } while (resultSet.next());
                pw.println("</html>");
            }

        } catch (SQLException e) {

        }


        if (name != null && user_id != null) {
            pw.println("<html>");
            pw.println("<h1> Hello, friend! Here are your tasks");
            pw.println("</h1>");

            pw.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
