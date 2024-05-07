package com.example.movie.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.movie.dto.PageRequestDto;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        log.info("Home 요청");
        return "redirect:/movie/list";
    }

    @GetMapping("/access-denied")
    public void getMethodName(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("접근 제한");
    }

    @GetMapping("/error")
    public String getError(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("404");
        return "/except/url404";
    }

    @ResponseBody
    @GetMapping("/auth")
    public Authentication getAuthenticationInfo() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        return authentication;
    }

}
