package com.coupang.marketplace.controller;

import com.coupang.marketplace.domain.User;
import com.coupang.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/users/sign-up")
    public String signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        userService.save(requestDto);
        return "success";
    }

    @PostMapping("/users/login")
    /*@ResponseStatus(HttpStatus.OK)*/
    public String signIn(@Valid @RequestBody SignInRequestDto requestDto) {
        Optional<User> user = userService.login(requestDto);
        
        //TO DO : 리턴값에 따라 다른 처리
        return "success";
    }
}
