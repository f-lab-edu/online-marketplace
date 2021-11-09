package com.coupang.marketplace.cart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.marketplace.auth.AuthRequired;
import com.coupang.marketplace.cart.service.CartService;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.product.controller.dto.SaveToCartRequest;
import com.coupang.marketplace.user.service.LoginService;

@RestController
public class CartController {

	private final CartService cartService;
	private final LoginService loginService;

	public CartController(CartService cartService, @Qualifier("userSessionLoginService") LoginService loginService){
		this.cartService = cartService;
		this.loginService = loginService;
	}

	@AuthRequired
	@PostMapping("/cart")
	public SuccessResponse saveProductsToCart(@Valid @RequestBody final SaveToCartRequest dto){
		long userId = loginService.getLoginUserId();
		cartService.saveProduct(userId, dto);
		return SuccessResponse.builder()
			.status(StatusEnum.OK)
			.message("장바구니에 상품 담기 성공")
			.build();
	}
}
