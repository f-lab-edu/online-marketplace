package com.coupang.marketplace.coupon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coupang.marketplace.coupon.domain.Coupon;
import com.coupang.marketplace.coupon.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CouponService {

	private final CouponRepository couponRepository;

	public List<Coupon> getAvailableCoupons(){
		return couponRepository.getCouponsBeforeExpirationTime();
	}
}
