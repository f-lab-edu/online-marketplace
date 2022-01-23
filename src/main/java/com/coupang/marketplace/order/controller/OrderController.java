package com.coupang.marketplace.order.controller;

import com.coupang.marketplace.order.domain.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.marketplace.auth.AuthRequired;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.order.controller.dto.OrderRequestDto;
import com.coupang.marketplace.order.service.OrderService;
import com.coupang.marketplace.user.service.LoginService;

import java.util.List;

@RestController
public class OrderController {

	private final OrderService orderService;
	private final LoginService loginService;

	public OrderController(OrderService orderService, @Qualifier("userSessionLoginService")LoginService loginService){
		this.orderService = orderService;
		this.loginService = loginService;
	}

// TODO(시은): [GET]/orders 코드 재작성
//	@Transactional(readOnly = true)
//	@AuthRequired
//	@GetMapping("/orders")
//	public SuccessResponse GetOrderInfo() {
//		long userId = loginService.getLoginUserId();
//		List<Order> orderBills = orderService.getOrderInfo(userId);
//		SuccessResponse res = SuccessResponse.builder()
//			.status(StatusEnum.CREATED)
//			.data(orderBills)
//			.message("주문정보 가져오기 성공")
//			.build();
//		return res;
//	}

	@Transactional
	@AuthRequired
	@PostMapping("/orders")
	public SuccessResponse order(@RequestBody OrderRequestDto orderRequestDto) {
		long userId = loginService.getLoginUserId();
		orderService.order(userId, orderRequestDto);
		SuccessResponse res = SuccessResponse.builder()
				.status(StatusEnum.CREATED)
				.message("주문하기 성공")
				.build();
		return res;
	}
}