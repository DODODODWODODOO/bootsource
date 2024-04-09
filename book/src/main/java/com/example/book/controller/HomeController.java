package com.example.book.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2

public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("home 요청");
        return "home";
    }

}
