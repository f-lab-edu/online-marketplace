package com.coupang.marketplace.user.service;

import com.coupang.marketplace.user.controller.SignInRequestDto;

public interface LoginService {

    void login(SignInRequestDto dto);

}
