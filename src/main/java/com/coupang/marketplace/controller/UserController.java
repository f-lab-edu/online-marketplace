package com.coupang.marketplace.controller;

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

    @PostMapping("/users/sign-up")
    public ResponseEntity signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        userService.join(requestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
