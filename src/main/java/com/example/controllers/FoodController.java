package com.example.controllers;

import com.example.database.Food;
import com.example.demo.NotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    @GetMapping
    public @ResponseBody List<Map<String, String>> allFood() {
        return list;
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Map<String, String> foodById(@PathVariable(name = "id") String id) {
        return list.stream()
                .filter(item -> item.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
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
        try(Session session = sessionFactory.openSession()) {
            session.persist(food);
            return "success";
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteFood(@PathVariable String id) {
        list.remove(Integer.parseInt(id));
    }
}
