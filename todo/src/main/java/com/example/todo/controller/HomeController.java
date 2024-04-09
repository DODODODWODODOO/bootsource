package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {

    // /로 접속 시 list 보이게
    @GetMapping("/")
    public String home() {
        log.info("home 요청 ");

        return "redirect:/todo/list";
    }
}
