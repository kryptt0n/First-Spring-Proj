package com.example.controllers;

import com.example.beans.MessageGeneratorRequest;
import com.example.beans.MessageGeneratorSession;
import jakarta.annotation.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestSessionController {

    @Resource(name = "sessionScope")
    private MessageGeneratorSession session;

    @Resource(name = "requestScope")
    private MessageGeneratorRequest request;

    @RequestMapping(path = "/requestSession")
    public String getMethod(Model model) {
        model.addAttribute("Initial",session.message);
        session.message = "new";
        model.addAttribute("Second", session.message);
        return "gg";
    }
}
