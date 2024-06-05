package com.example.springteamwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {
    @GetMapping("/about_us")
    public String aboutUs() {
        return "about_us";
    }
}
