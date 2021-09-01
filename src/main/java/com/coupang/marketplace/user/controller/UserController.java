package com.coupang.marketplace.user.controller;

import static com.coupang.marketplace.auth.LoginType.*;

import com.coupang.marketplace.auth.LoginAuth;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.user.controller.dto.SignInRequestDto;
import com.coupang.marketplace.user.controller.dto.SignUpRequestDto;
import com.coupang.marketplace.user.controller.dto.UpdateRequestDto;
import com.coupang.marketplace.user.service.LoginService;
import com.coupang.marketplace.user.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    @Qualifier("sessionLoginService")
    private final LoginService loginService;

    @PostMapping("/users/sign-up")
    public SuccessResponse signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        userService.join(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(StatusEnum.CREATED)
                .message("회원가입 성공")
                .build();
        return res;
    }

    @PostMapping("/users/login")
    public SuccessResponse loginUser(@Valid @RequestBody SignInRequestDto requestDto) {
        loginService.login(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(StatusEnum.OK)
                .message("로그인 성공")
                .build();
        return res;
    }

    @GetMapping("/users/logout")
    public void logoutUser(){
        loginService.logout();
    }

    @LoginAuth(type = USER)
    @PutMapping("/my-info")
    public SuccessResponse updateUser(@Valid @RequestBody UpdateRequestDto requestDto){
        long id = loginService.getLoginUserId();
        userService.updateUser(id, requestDto);
        SuccessResponse res = SuccessResponse.builder()
            .status(StatusEnum.OK)
            .message("회원 정보 수정 성공")
            .build();
        return res;
    }
}
