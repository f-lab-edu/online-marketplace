package com.coupang.marketplace.coupon.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.marketplace.coupon.domain.Coupon;
import com.coupang.marketplace.coupon.service.CouponService;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CouponController {

	private final CouponService couponService;

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
}