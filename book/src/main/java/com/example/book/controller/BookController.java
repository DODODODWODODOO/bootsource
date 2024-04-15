package com.example.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.book.dto.BookDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.entity.Book;
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
    public void bookList(Model model, @ModelAttribute("requestDto") PageRequestDto requestDto) { // ModelAttribute
                                                                                                 // 별칭(안해도됨)
        log.info("list 요청");
        PageResultDto<BookDto, Book> result = service.getList(requestDto);
        model.addAttribute("result", result);

    }

    @GetMapping(value = { "/read", "/modify" })
    public void read(Long id, Model model, @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("read or modify 요청");

        model.addAttribute("bookDto", service.getRow(id));
    }

    @PostMapping("/modify")
    public String postModify(BookDto updateDto, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("업데이트 요청 {}", updateDto);
        log.info("page 나누기 정보 {}", requestDto);

        Long id = service.bookUpdate(updateDto);

        // 조회화면으로 이동
        rttr.addAttribute("id", id); // ?~~~ 주소줄 방식
        // 페이지 나누기 정보
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:/book/read";
    }

    @GetMapping(value = { "/create" })
    public void create(BookDto dto, Model model, @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("create 요청");

        // 테이블에 있는 카테고리 명 보여주기
        model.addAttribute("categories", service.categoryNameList());
    }

    @PostMapping("/create")
    public String postCreate(@Valid BookDto dto, BindingResult result, RedirectAttributes rttr, Model model,
            @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("book post 요청 {}" + dto);
        if (result.hasErrors()) {
            model.addAttribute("categories", service.categoryNameList());
            return "/book/create";
        }

        // insert 작성
        Long id = service.bookCreate(dto);

        // 페이지 나누기 정보
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        rttr.addFlashAttribute("msg", id);

        return "redirect:/book/list";
    }

    @PostMapping("/delete")
    public String postDelete(Long id, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("도서 삭제 요청 {}", id);
        service.bookDelete(id);

        // 페이지 나누기 정보
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:/book/list";
    }

}
