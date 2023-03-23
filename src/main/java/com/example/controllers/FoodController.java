package com.example.controllers;

import com.example.database.Food;
import com.example.demo.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "food")
public class FoodController {

    private List<Map<String, String>> list = new ArrayList<>();
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

    @PostMapping(path = "/add")
    public @ResponseBody void addFood(@RequestParam String name) {
        list.add(new HashMap<>(){{put("id", String.valueOf(++counter)); put("name", name);}});
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteFood(@PathVariable String id) {
        list.remove(Integer.parseInt(id));
    }
}
