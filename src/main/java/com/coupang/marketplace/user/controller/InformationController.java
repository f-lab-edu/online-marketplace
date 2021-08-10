package com.coupang.marketplace.user.controller;

import static com.coupang.marketplace.auth.LoginType.*;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.marketplace.auth.LoginAuth;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.user.controller.dto.UpdateRequestDto;
import com.coupang.marketplace.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class InformationController {

	private final UserService userService;

	@LoginAuth(type = USER)
	@PutMapping("/mypage/user-information/{id}")
	public SuccessResponse updateUser(@PathVariable Long id, @Valid @RequestBody UpdateRequestDto requestDto){
		userService.updateUser(id, requestDto);
		SuccessResponse res = SuccessResponse.builder()
			.status(StatusEnum.OK)
			.message("회원 정보 수정 성공")
			.build();
		return res;
	}
}
