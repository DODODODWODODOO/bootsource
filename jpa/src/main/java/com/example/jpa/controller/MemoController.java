package com.example.jpa.controller;

import java.util.List;

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

import com.example.jpa.dto.MemoDto;
import com.example.jpa.service.MemoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequiredArgsConstructor // MemoService 주입한 걸 생성해라
@RequestMapping("/memo")
public class MemoController {

    private final MemoServiceImpl service;

    // /memo/list + GET
    @GetMapping("/list")
    public void list(Model model) {
        log.info("list 요청");

        List<MemoDto> list = service.getMemoList();

        // list를 list.html 보여주고 싶으면
        model.addAttribute("list", list);
    }

    // /memo/read?mno=3 + GET
    // /memo/modify?mno=3 + GET : 수정을 위해 화면 보여주기
    @GetMapping({ "/read", "/modify" }) // {"",""} 여러개의 경로 지정 가능
    public void readMemo(@RequestParam Long mno, Model model) {
        log.info("read form 요청");

        MemoDto mDto = service.getMemo(mno);
        model.addAttribute("mDto", mDto);

        // 템플릿 찾기
        // /memo/read => templates 폴더 아래 /memo/read.html
        // /memo/modify => templates 폴더 아래 /memo/modify.html

    }

    // /memo/modify + POST : 실제 수정 요청
    @PostMapping("/modify")
    public String modifyPost(MemoDto mDto, RedirectAttributes rttr) {
        log.info("modify 요청" + mDto);
        MemoDto updateDto = service.updateMemo(mDto);

        // 수정 완료 시 /memo/read?mno= 이동
        rttr.addAttribute("mno", updateDto.getMno());
        return "redirect:/memo/read";
    }

    // memo/delete?mno=3 + GET : 삭제 요청
    @GetMapping("/delete")
    public String deleteGet(@RequestParam Long mno) {
        log.info("메모 삭제 요청 {}", mno);
        service.deleteMemo(mno);

        // 삭제 성공 시 리스트
        return "redirect:/memo/list";
    }

    // /memo/create?mno=3 + GET : 입력을 위해 화면 보여주기 요청
    @GetMapping("/create")
    public void getCreate(@ModelAttribute("mDto") MemoDto mDto) { // Dto 객체 이름 지정
        log.info("메모 입력 폼 요청");
    }

    // /memo/create?mno=3 + POST : 실제 입력 요청
    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("mDto") MemoDto mDto, BindingResult result,
            RedirectAttributes rttr) {
        log.info("create post 요청 {}", mDto);

        if (result.hasErrors()) {
            return "/memo/create";
        }
        service.insertMemo(mDto);

        rttr.addFlashAttribute("result", "SUCCESS");
        return "redirect:/memo/list";
    }

}
