package com.example.controllers;

import com.example.database.Food;
import com.example.demo.NotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "food")
public class FoodController {

    private List<Map<String, String>> list = new ArrayList<>();

    @Autowired
    private SessionFactory sessionFactory;
    private int counter = 2;

    {
        list.add(new HashMap<>(){{put("id", "0"); put("name", "banana");}});
        list.add(new HashMap<>(){{put("id", "1"); put("name", "apple");}});
        list.add(new HashMap<>(){{put("id", "2"); put("name", "banana");}});
    }


    @GetMapping(path = "/{id}")
    public @ResponseBody Map<String, String> foodById(@PathVariable(name = "id") String id) {
        return list.stream()
                .filter(item -> item.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping
    public @ResponseBody List<Food> allFood() {
        try(Session session = sessionFactory.openSession()) {
            Query<Food> allFood = session.createQuery("from Food", Food.class);
            return allFood.list();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @PostMapping(path = "/addFood")
    public @ResponseBody void addFood(@RequestParam String name) {
        try(Session session = sessionFactory.openSession()) {
            Food food = new Food();
            food.name = name;
            session.persist(food);
        }
    }

    @PostMapping(path = "/add")
    public String saveFood(@ModelAttribute Food food, BindingResult result, Model model) {
        Transaction tra = null;
        try(Session session = sessionFactory.openSession()) {
            tra = session.beginTransaction();
            session.persist(food);
            tra.commit();
        } catch (Exception e) {
            tra.rollback();
        }
            return "success";
    }

    @DeleteMapping(path = "/delete")
    public void foodDeleted(@ModelAttribute Food food, BindingResult result, Model model) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(food);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteFood(@PathVariable String id) {
        list.remove(Integer.parseInt(id));
    }
}
