package com.example.mall_management.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "index"; // templates/index.html
    }

    @GetMapping("/about")
    public String about() {
        return "about"; // templates/about.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact"; // templates/contact.html
    }

    @GetMapping("/services")
    public String services() {
        return "services"; // templates/services.html
    }
}
