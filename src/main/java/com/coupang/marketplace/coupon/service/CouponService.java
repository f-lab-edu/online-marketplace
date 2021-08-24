package com.coupang.marketplace.coupon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coupang.marketplace.coupon.domain.Coupon;
import com.coupang.marketplace.coupon.domain.UserCoupon;
import com.coupang.marketplace.coupon.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CouponService {

	private final CouponRepository couponRepository;

	public List<Coupon> getAvailableCoupons(){
		return couponRepository.getCouponsBeforeExpirationTime();
	}

	public void saveCoupon(long userId, long id){
		if(!checkIsAvailableCoupon(id))
			throw new IllegalArgumentException("사용할 수 없는 쿠폰입니다.");
		if(checkIsAlreadyHave(userId, id))
			throw new IllegalArgumentException("이미 받은 쿠폰입니다.");
		UserCoupon userCoupon = UserCoupon.builder()
			.userId(userId)
			.couponId(id)
			.build();
		couponRepository.insertUserCoupon(userCoupon);
	}

	public boolean checkIsAvailableCoupon(long id){
		return couponRepository.findAvailableCouponById(id).isPresent();
	}

	public boolean checkIsAlreadyHave(long userId, long id) {
		return couponRepository.findUserCouponById(userId, id).isPresent();
	}
}
