package com.coupang.marketplace.user.service;

import com.coupang.marketplace.user.controller.dto.SignInRequestDto;

public interface LoginService {

    void login(SignInRequestDto dto);

    void logout();

    long getLoginUserId();
}
