package com.coupang.marketplace.coupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.marketplace.auth.AuthRequired;
import com.coupang.marketplace.coupon.domain.Coupon;
import com.coupang.marketplace.coupon.service.CouponService;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.user.service.LoginService;

@RestController
public class CouponController {

	private final CouponService couponService;
	private final LoginService loginService;

	public CouponController(CouponService couponService, @Qualifier("userSessionLoginService") LoginService loginService){
		this.couponService = couponService;
		this.loginService = loginService;
	}

	@GetMapping("/available-coupons")
	public SuccessResponse getAvailableCoupons(){
		List<Coupon> coupons = couponService.getAvailableCoupons();
		SuccessResponse res = SuccessResponse.builder()
			.status(StatusEnum.OK)
			.message("사용가능한 쿠폰 목록 가져오기 성공")
			.data(coupons)
			.build();
		return res;
	}

	@AuthRequired
	@PostMapping("/available-coupons/{id}")
	public SuccessResponse saveCoupon(@PathVariable("id") final Long id){
		Long userId = loginService.getLoginUserId();
		couponService.saveCoupon(id, userId);
		SuccessResponse res = SuccessResponse.builder()
			.status(StatusEnum.CREATED)
			.message("쿠폰받기 성공")
			.build();
		return res;
	}
}
