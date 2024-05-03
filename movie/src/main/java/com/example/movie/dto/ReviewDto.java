package com.example.movie.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long reviewNo;
    private int grade;
    private String text;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    // 맴버 관계
    private Long mid;
    private String email;
    private String nickname;

    // 무비 관계
    private Long mno;

}
