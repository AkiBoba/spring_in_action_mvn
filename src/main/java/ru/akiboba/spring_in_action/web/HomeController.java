package ru.akiboba.spring_in_action.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "home")
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
