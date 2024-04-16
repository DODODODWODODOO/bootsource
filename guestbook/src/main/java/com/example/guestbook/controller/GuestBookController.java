package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.service.GuestBookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
@Log4j2
@RequestMapping("/guestbook")
public class GuestBookController {

    private final GuestBookService service;

    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("list 요청");

        PageResultDto<GuestBookDto, GuestBook> result = service.getList(requestDto);

        model.addAttribute("result", result);
    }

    @GetMapping({ "/read", "/modify" })
    public void getread(@RequestParam Long gno, @ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("read or modify 요청");

        model.addAttribute("dto", service.getRow(gno));
    }

    @PostMapping("/modify")
    public String postmodify(GuestBookDto modifyDto, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("modify 요청 {}", modifyDto);

        service.modify(modifyDto);

        rttr.addAttribute("gno", modifyDto.getGno());
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:/guestbook/read";
    }

    @PostMapping("/remove")
    public String postRemove(@RequestParam Long gno, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {

        service.remove(gno);

        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:/guestbook/list";
    }

    @GetMapping("/create")
    public void getCreate(GuestBookDto dto, @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("등록 폼 요청");
    }

    @PostMapping("/create") // valid 다음에 binding 순서 지키기
    public String postCreate(@Valid GuestBookDto dto, BindingResult result, RedirectAttributes rttr,
            @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("등록 요청 {}", dto);

        if (result.hasErrors()) {
            return "/guestbook/create";
        }

        Long gno = service.insert(dto);

        rttr.addFlashAttribute("msg", gno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:/guestbook/list";
    }

}
