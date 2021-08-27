package com.coupang.marketplace.user.controller;

import static com.coupang.marketplace.auth.LoginType.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.marketplace.auth.LoginAuth;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.user.controller.dto.UpdateRequestDto;
import com.coupang.marketplace.user.service.LoginService;
import com.coupang.marketplace.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MypageController {

	private final UserService userService;
	@Qualifier("sessionLoginService")
	private final LoginService loginService;

	@LoginAuth(type = USER)
	@PutMapping("/mypage/my-info")
	public SuccessResponse updateUser(@Valid @RequestBody UpdateRequestDto requestDto){
		Long id = loginService.getLoginUserId();
		userService.updateUser(id, requestDto);
		SuccessResponse res = SuccessResponse.builder()
			.status(StatusEnum.OK)
			.message("회원 정보 수정 성공")
			.build();
		return res;
	}
}
