package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class TodoController {

    // /로 접속 시 list.html 보이게
    @GetMapping(value = { "/", "/todo/list" })
    public String list() {
        log.info("list 요청 ");
        return "/todo/list";
    }

}
