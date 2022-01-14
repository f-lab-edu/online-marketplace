package com.coupang.marketplace.coupon.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.coupang.marketplace.cart.domain.Cart;
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
		List<Coupon> coupons = couponRepository.getAllCoupons();

		List<Coupon> availableCoupons = coupons.stream()
				.filter(coupon -> coupon.getExpirationTime().isAfter(ZonedDateTime.now()))
				.collect(Collectors.toList());

		return availableCoupons;
	}

	@Transactional
	public void saveCoupon(long id, long userId){
		if(!checkIsAvailableCoupon(id))
			throw new IllegalArgumentException("사용할 수 없는 쿠폰입니다.");
		int maxCouponCount = couponRepository.getMaxCouponCount(id);
		int issuedCouponCount = 0;
		if(checkIsAlreadyHave(id, userId))
			issuedCouponCount = couponRepository.getIssuedCount(id, userId);

		if(maxCouponCount == issuedCouponCount)
			throw new IllegalArgumentException("더 이상 발급이 불가능합니다.");

		if(!checkIsAlreadyHave(id, userId)){
			UserCoupon userCoupon = UserCoupon.builder()
				.userId(userId)
				.couponId(id)
				.issuedCount(issuedCouponCount+1)
				.build();
			couponRepository.insertUserCoupon(userCoupon);
		}
		else
			couponRepository.updateIssuedCount(id, userId, issuedCouponCount+1);
	}

	public boolean checkIsAvailableCoupon(long id){
		ZonedDateTime expirationTime = couponRepository.findCouponById(id).get().getExpirationTime();
		return expirationTime.isAfter(ZonedDateTime.now());
	}

	public boolean checkIsAlreadyHave(long couponId, long userId) {
		return couponRepository.findUserCouponById(couponId, userId).isPresent();
	}

	public long getDiscountPriceByCoupon(long userId, Optional<Long> couponId, List<Cart> cartProducts, long totalProductPrice){
		if(couponId.isEmpty())
			return 0;
		if(!checkIsAlreadyHave(couponId.get(), userId))
			throw new IllegalArgumentException("발급받지 않은 쿠폰입니다.");
		if(!checkIsAvailableCoupon(couponId.get()))
			throw new IllegalArgumentException("기간이 지난 쿠폰입니다.");

		Optional<Coupon> coupon = couponRepository.findCouponById(couponId.get());
		List<Long> productsId = cartProducts.stream()
				.map(cartProduct -> cartProduct.getProductId())
				.collect(Collectors.toList());
		if(!productsId.contains(coupon.get().getProductId()))
			throw new IllegalArgumentException("상품에 적용되는 쿠폰이 아닙니다.");
		if(coupon.get().getMinPrice() > totalProductPrice)
			throw new IllegalArgumentException("최소주문금액을 만족하지 못합니다.");

		Optional<UserCoupon> userCoupon = couponRepository.findUserCouponById(couponId.get(), userId);
		if(userCoupon.get().getIssuedCount() - userCoupon.get().getUseCount() <= 0)
			throw new IllegalArgumentException("이미 사용 완료한 쿠폰입니다.");

		return coupon.get().getDiscountPrice();
	}

	public void IncreaseUseCount(long userId, long couponId){
		Optional<UserCoupon> userCoupon = couponRepository.findUserCouponById(couponId, userId);
		couponRepository.updateUseCount(userId, couponId, userCoupon.get().getUseCount()+1);
	}
}
