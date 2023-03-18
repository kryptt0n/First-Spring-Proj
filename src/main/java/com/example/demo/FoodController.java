package com.example.demo;

import com.example.database.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/food")
public class FoodController {
    @Autowired
    private FoodRepository foodRepository;

    @PostMapping("/add")
    public @ResponseBody String addNewFood(@RequestParam String name) {
        Food food = new Food();
        food.name = name;
        foodRepository.save(food);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Food> getAllFood() {
        return foodRepository.findAll();
    }
}
