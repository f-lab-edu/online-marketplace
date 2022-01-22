package com.coupang.marketplace.order.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.marketplace.auth.AuthRequired;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.order.controller.dto.OrderInfo;
import com.coupang.marketplace.order.service.OrderService;
import com.coupang.marketplace.user.service.LoginService;

@RestController
public class OrderController {

	private final OrderService orderService;
	private final LoginService loginService;

	public OrderController(OrderService orderService, @Qualifier("userSessionLoginService")LoginService loginService){
		this.orderService = orderService;
		this.loginService = loginService;
	}

	@AuthRequired
	@GetMapping("/orders")
	public SuccessResponse GetOrderInfo() {
		long userId = loginService.getLoginUserId();
		OrderInfo orderInfo = orderService.getOrderInfo(userId);
		SuccessResponse res = SuccessResponse.builder()
			.status(StatusEnum.CREATED)
			.data(orderInfo)
			.message("주문정보 가져오기 성공")
			.build();
		return res;
	}
}