package com.example.controllers;

import com.example.database.Food;
import com.example.database.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@PropertySource("classpath:application.properties")
public class SimpleController {

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${authorName}")
    private String authorName;

    @GetMapping(path = "/home")
    public String homePage(Model model) {
        model.addAttribute("authorName", authorName);
        try(Session session = sessionFactory.openSession()) {
            Query<Food> query = session.createQuery("from Food", Food.class);
            model.addAttribute("foods", query.list());
        }
        return "home";
    }

    @GetMapping(path = "/foodAdd")
    public String foodPage(Model model) {
        model.addAttribute("food", new Food());
        return "addFood";
    }

    @GetMapping(path = "/foodAll")
    public String foodAll(Model model) {
        List<Food> foodList = null;
        try(Session session = sessionFactory.openSession()) {
            Query<Food> foods = session.createQuery("from Food", Food.class);
            foodList = foods.list();
        }
        model.addAttribute("foods", foodList);
        return "allFood";
    }

}
