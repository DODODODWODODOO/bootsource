package com.example.board.dto;

import java.time.LocalDateTime;

import com.example.board.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReplyDto {

    private Long rno;

    private String text; // 댓글내용

    private String replyer; // 댓글작성자

    private Long bno; // 부모글 번호

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}