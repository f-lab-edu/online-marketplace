package com.coupang.marketplace.coupon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coupang.marketplace.coupon.domain.Coupon;
import com.coupang.marketplace.coupon.domain.UserCoupon;
import com.coupang.marketplace.coupon.repository.CouponRepository;
import com.coupang.marketplace.global.util.session.HttpSessionUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CouponService {

	private final CouponRepository couponRepository;

	private final HttpSessionUtil httpSessionUtil;

	public List<Coupon> getAvailableCoupons(){
		return couponRepository.getCouponsBeforeExpirationTime();
	}

	public void saveCoupon(long id){
		if(!checkIsAvailableCoupon(id))
			throw new IllegalArgumentException("사용할 수 없는 쿠폰입니다.");
		if(checkIsAlreadyHave(id))
			throw new IllegalArgumentException("이미 받은 쿠폰입니다.");
		UserCoupon userCoupon = UserCoupon.builder()
			.userId((long)httpSessionUtil.getAttribute())
			.couponId(id)
			.build();
		couponRepository.insertUserCoupon(userCoupon);
	}

	public boolean checkIsAvailableCoupon(long id){
		return couponRepository.findAvailableCouponById(id).isPresent();
	}

	public boolean checkIsAlreadyHave(long id) {
		return couponRepository.findByCouponId(id).isPresent();
	}
}
