package com.coupang.marketplace.payment.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.marketplace.auth.AuthRequired;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.order.controller.dto.OrderRequestDto;
import com.coupang.marketplace.order.service.OrderService;
import com.coupang.marketplace.user.service.LoginService;

@RestController
public class PaymentController {

	private final LoginService loginService;
	private final OrderService orderService;

	public PaymentController(OrderService orderService, @Qualifier("userSessionLoginService")LoginService loginService){
		this.orderService = orderService;
		this.loginService = loginService;
	}

	@Transactional
	@AuthRequired
	@PostMapping("/pay")
	public SuccessResponse order(@RequestBody OrderRequestDto orderRequestDto){
		long userId = loginService.getLoginUserId();
		orderService.order(userId, orderRequestDto);
		SuccessResponse res = SuccessResponse.builder()
			.status(StatusEnum.CREATED)
			.message("결제하기 성공")
			.build();
		return res;
	}
}
