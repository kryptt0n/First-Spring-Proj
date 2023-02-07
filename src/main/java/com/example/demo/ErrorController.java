package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @GetMapping("/error")
    public String error() {
        String res = "";
        res += "<html>";
        res += "<h>";
        res += "error :(";
        res += "</h>";
        res += "/<html>";
        return res;
    }
}
