package com.coupang.marketplace.coupon.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupang.marketplace.coupon.domain.Coupon;
import com.coupang.marketplace.coupon.domain.UserCoupon;
import com.coupang.marketplace.coupon.repository.CouponRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class CouponService {

	private final CouponRepository couponRepository;

	public List<Coupon> getAvailableCoupons(){
		return couponRepository.getCouponsBeforeExpirationTime();
	}

	@Transactional
	public void saveCoupon(long id, long userId){
		if(!checkIsAvailableCoupon(id))
			throw new IllegalArgumentException("사용할 수 없는 쿠폰입니다.");
		int maxCouponCount = couponRepository.getMaxCouponCount(id);
		int issuedCouponCount = 0;
		if(checkIsAlreadyHave(id, userId))
			issuedCouponCount = couponRepository.getIssuedCouponCount(id, userId);

		if(maxCouponCount == issuedCouponCount)
			throw new IllegalArgumentException("더 이상 발급이 불가능합니다.");

		if(!checkIsAlreadyHave(id, userId)){
			UserCoupon userCoupon = UserCoupon.builder()
				.userId(userId)
				.couponId(id)
				.issuedCouponCount(issuedCouponCount+1)
				.build();
			couponRepository.insertUserCoupon(userCoupon);
		}
		else
			couponRepository.updateIssuedCouponCount(id, userId, issuedCouponCount+1);
	}

	public boolean checkIsAvailableCoupon(long id){
		ZonedDateTime expirationTime = couponRepository.findCouponById(id).getExpirationTime();
		return expirationTime.isAfter(ZonedDateTime.now());
	}

	public boolean checkIsAlreadyHave(long id, long userId) {
		return couponRepository.findUserCouponById(id, userId).isPresent();
	}
}
