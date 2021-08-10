package com.coupang.marketplace.user.controller;

import com.coupang.marketplace.fixture.UserFixture.*;
import com.coupang.marketplace.user.controller.dto.SignInRequestDto;
import com.coupang.marketplace.user.controller.dto.SignUpRequestDto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class UserControllerTest extends ControllerTest {

    @DisplayName("이메일이 중복된 회원이 아니라면 회원 가입에 성공한다.")
    @Test
    void signUp() throws Exception {
        // given
        final SignUpRequestDto dto = SignUpRequestDto.builder()
                .name(User1.NAME)
                .email(User1.EMAIL)
                .password(User1.PASSWORD)
                .phone(User1.PHONE)
                .build();

        // when
        final ResultActions actions = mvc.perform(post("/users/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print());

        // then
        actions
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("이메일이 중복된 사용자는 회원가입에 실패한다.")
    @Test
    void signUpWithDuplicateEmail() throws Exception {
        // given
        final SignUpRequestDto dto1 = SignUpRequestDto.builder()
                .name(User1.NAME)
                .email(User1.EMAIL)
                .password(User1.PASSWORD)
                .phone(User1.PHONE)
                .build();

        final SignUpRequestDto dto2 = SignUpRequestDto.builder()
                .name(User2.NAME)
                .email(User1.EMAIL)
                .password(User2.PASSWORD)
                .phone(User2.PHONE)
                .build();

        // when
        mvc.perform(post("/users/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto1)));

        final ResultActions actions = mvc.perform(post("/users/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto2)))
                .andDo(print());

        // then
        actions
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @DisplayName("회원의 이메일이 존재하고 패스워드가 일치하면 로그인에 성공한다.")
    @Test
    void signIn() throws Exception {
        // given
        final SignUpRequestDto joinDto = SignUpRequestDto.builder()
            .name(User1.NAME)
            .email(User1.EMAIL)
            .password(User1.PASSWORD)
            .phone(User1.PHONE)
            .build();

        final SignInRequestDto LoginDto = SignInRequestDto.builder()
            .email(User1.EMAIL)
            .password(User1.PASSWORD)
            .build();

        // when
        mvc.perform(post("/users/sign-up")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(joinDto)));

        final ResultActions actions = mvc.perform(post("/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(LoginDto)))
            .andDo(print());

        // then
        actions
            .andExpect(status().isOk())
            .andDo(print());
    }

    @DisplayName("이메일이 존재하지 않은 사용자는 로그인에 실패한다.")
    @Test
    void signInWithNotFoundEmail() throws Exception {
        // given
        final SignInRequestDto dto = SignInRequestDto.builder()
            .email(User1.EMAIL)
            .password(User1.PASSWORD)
            .build();

        // when
        final ResultActions actions = mvc.perform(post("/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andDo(print());

        // then
        actions
            .andExpect(status().isBadRequest())
            .andDo(print());
    }

    @DisplayName("이메일이 존재하지만 패스워드가 일치하지 않은 사용자는 로그인에 실패한다.")
    @Test
    void signInWithInvalidPassword() throws Exception {
        // given
        final SignUpRequestDto JoinDto = SignUpRequestDto.builder()
            .name(User1.NAME)
            .email(User1.EMAIL)
            .password(User1.PASSWORD)
            .phone(User1.PHONE)
            .build();

        final SignInRequestDto LoginDto = SignInRequestDto.builder()
            .email(User1.EMAIL)
            .password(User2.PASSWORD)
            .build();

        // when
        mvc.perform(post("/users/sign-up")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(JoinDto)));

        final ResultActions actions = mvc.perform(post("/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(LoginDto)))
            .andDo(print());

        // then
        actions
            .andExpect(status().isBadRequest())
            .andDo(print());
    }
}
