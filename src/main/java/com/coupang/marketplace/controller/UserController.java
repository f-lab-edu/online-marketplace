package com.coupang.marketplace.controller;

import com.coupang.marketplace.service.LoginService;
import com.coupang.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/users/sign-up")
    public ResponseEntity<SuccessResponse> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        userService.join(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(StatusEnum.CREATED)
                .message("회원가입 성공")
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/users/login")
    public ResponseEntity<SuccessResponse> signIn(@Valid @RequestBody SignInRequestDto requestDto) {
        loginService.login(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(StatusEnum.OK)
                .message("로그인 성공")
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
