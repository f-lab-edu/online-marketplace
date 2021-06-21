package com.coupang.marketplace.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class SignInRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 양식을 지켜주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Builder
    public SignInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
