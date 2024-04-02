package com.example.web1.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

// Data 어노테이션이 getter,setter,... 다 가지고 있음
@Data
public class LoginDto {

    @Email(message = "이메일을 확인해 주세요") // (입력이 되었을때)이메일 검증
    @NotEmpty // @NotNull + "" 값 불가 (비어 있을 수 없습니다), 없는경우 500에러 뜸
    private String email;

    @Length(min = 2, max = 6) // 길이검증 (길이가 2에서 6 사이어야 합니다)
    // @NotBlank // @NotEmpty + "" 값 불가
    private String name;
}
