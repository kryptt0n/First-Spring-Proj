package com.example.controllers;

import com.example.database.Task;
import com.example.database.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
public class HibernateController {

    @Autowired
    private SessionFactory sessionFactory;

    @GetMapping("/hibernate")
    public String hibernate() {
        List<Integer> levels = new ArrayList<>();
        Collections.addAll(levels, 100, 200, 1);
        getUsersWithLevel(levels).forEach(System.out::println);
        return "";
    }

    private User createUser(String name, Integer lvl) {
        User user = new User();
        user.name = name;
        user.level = lvl;
        user.created_date = new Date(new java.util.Date().getTime());
        return user;
    }

    private List<User> getAllUsers() {
        try(Session session = sessionFactory.openSession()) {
            Query<User> userQuery = session.createQuery("from User", User.class);
            return userQuery.list();
        }
    }

    private List<Task> getAllTasks() {
        try(Session session = sessionFactory.openSession()) {
            Query<Task> taskQuery = session.createQuery("from Task", Task.class);
            return taskQuery.list();
        }
    }

    private User getUserByID(Integer id) {
        try(Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }

    private void getMaxLevelAndDate() {
        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("select max(level), min(created_date) from User ");
            List<?> l = query.list();
            l.forEach(System.out::println);
        }
    }

    private List<Task> getTasksForUser(User user) {
        try(Session session = sessionFactory.openSession()) {
            Query<Task> query = session.createQuery("FROM Task WHERE user_id = :id");
            query.setParameter("id", user);
            return query.list();
        }
    }

    private boolean saveUser(User user) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private List<User> getUsersWithLevel(List<Integer> levels) {
        try(Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where level in (:levels)", User.class);
            query.setParameter("levels", levels);
            return query.list();
        }
    }
}
