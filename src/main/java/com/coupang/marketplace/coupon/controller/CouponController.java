package com.coupang.marketplace.coupon.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.marketplace.coupon.domain.Coupon;
import com.coupang.marketplace.coupon.service.CouponService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CouponController {

	private final CouponService couponService;

	@GetMapping("/coupons")
	public List<Coupon> getAvailableCoupons(){
		return couponService.getAvailableCoupons();
	}
}
