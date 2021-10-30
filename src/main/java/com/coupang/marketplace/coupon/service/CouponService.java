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
	public void saveCoupon(long userId, long id){
		if(!checkIsAvailableCoupon(id))
			throw new IllegalArgumentException("사용할 수 없는 쿠폰입니다.");
		if(checkIsAlreadyHave(userId, id))
			throw new IllegalArgumentException("이미 받은 쿠폰입니다.");
		UserCoupon userCoupon = UserCoupon.builder()
			.userId(userId)
			.couponId(id)
			.build();
		Long insertCouponCount = couponRepository.insertUserCoupon(userCoupon);
		if(insertCouponCount != 1){
			log.error("insert coupon Error! userCoupon : {}, insertCouponCount : {}", userCoupon, insertCouponCount);
			throw new RuntimeException("쿠폰 저장 오류");
		}
	}

	public boolean checkIsAvailableCoupon(long id){
		ZonedDateTime expirationTime = couponRepository.findCouponById(id).getExpirationTime();
		return expirationTime.isAfter(ZonedDateTime.now());
	}

	public boolean checkIsAlreadyHave(long userId, long id) {
		return couponRepository.findUserCouponById(userId, id).isPresent();
	}
}
