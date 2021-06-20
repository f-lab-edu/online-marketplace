package com.coupang.marketplace.controller;

import com.coupang.marketplace.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 양식을 지켜주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp="[0-9]{10,11}", message = "10-11 자리의 전화번호를 입력해야 해요.")
    private String phone;

    @Builder
    public SignUpRequestDto(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .phone(phone)
                .build();
    }
}
