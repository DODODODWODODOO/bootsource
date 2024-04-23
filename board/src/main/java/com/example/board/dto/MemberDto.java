package com.example.board.dto;

import com.example.board.constant.MemberRole;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    @NotBlank(message = "이메일을 입력해 주세요")
    private String email;

    @NotBlank(message = "이름을 입력해 주세요")
    private String name;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String password;

    private MemberRole memberRole;
}
