package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GreetingController {
    private static final String TEMPLATE = "Wassup, %s";
    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world!") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
