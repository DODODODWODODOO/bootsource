package com.example.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.book.dto.BookDto;
import com.example.book.service.BookService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/list")
    public void bookList(Model model) {
        log.info("list 요청");
        List<BookDto> list = service.getList();
        model.addAttribute("list", list);

    }

    @GetMapping(value = { "/read", "/modify" })
    public void read(Long id, Model model) {
        log.info("read or modify 요청");

        model.addAttribute("bookDto", service.getRow(id));
    }

    @PostMapping("/modify")
    public String postModify(BookDto updateDto, RedirectAttributes rttr) {
        log.info("업데이트 요청 {}", updateDto);

        Long id = service.bookUpdate(updateDto);

        // 조회화면으로 이동
        rttr.addAttribute("id", id); // ?~~~ 주소줄 방식
        return "redirect:/book/read";
    }

    @GetMapping(value = { "/create" })
    public void create(BookDto dto, Model model) {
        log.info("create 요청");

        // 테이블에 있는 카테고리 명 보여주기
        model.addAttribute("categories", service.categoryNameList());
    }

    @PostMapping("/create")
    public String postCreate(@Valid BookDto dto, BindingResult result, RedirectAttributes rttr, Model model) {
        log.info("book post 요청 {}" + dto);
        if (result.hasErrors()) {
            model.addAttribute("categories", service.categoryNameList());
            return "/book/create";
        }

        // insert 작성
        Long id = service.bookCreate(dto);
        rttr.addFlashAttribute("result", id);

        return "redirect:/book/list";
    }

    @PostMapping("/delete")
    public String postDelete(Long id) {
        log.info("도서 삭제 요청 {}", id);
        service.bookDelete(id);

        return "redirect:/book/list";
    }

}
