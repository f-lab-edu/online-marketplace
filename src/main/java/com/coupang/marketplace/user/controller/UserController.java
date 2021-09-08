package com.coupang.marketplace.user.controller;

import com.coupang.marketplace.auth.AuthRequired;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.user.controller.dto.*;
import com.coupang.marketplace.user.service.LoginService;
import com.coupang.marketplace.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public SuccessResponse signUp(@Valid @RequestBody final SignUpRequestDto requestDto) {
        userService.join(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(StatusEnum.CREATED)
                .message("회원가입 성공")
                .build();
        return res;
    }

    @PostMapping("/login")
    public SuccessResponse loginUser(@Valid @RequestBody final SignInRequestDto requestDto) {
        loginService.login(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(StatusEnum.OK)
                .message("로그인 성공")
                .build();
        return res;
    }

    @GetMapping("/logout")
    public void logoutUser(){
        loginService.logout();
    }

    @AuthRequired
    @PutMapping("/my-info")
    public void updateUser(@Valid @RequestBody final UpdateUserRequestDto requestDto){
        long id = loginService.getLoginUserId();
        userService.updateUser(id, requestDto);
    }
}
