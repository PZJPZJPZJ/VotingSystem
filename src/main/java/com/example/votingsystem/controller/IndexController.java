package com.example.votingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String toRedirect() {
        return "redirect:/visitor/index";
    }
}
