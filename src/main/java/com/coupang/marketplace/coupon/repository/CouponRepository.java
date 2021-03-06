package com.coupang.marketplace.coupon.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.coupang.marketplace.coupon.domain.Coupon;
import com.coupang.marketplace.coupon.domain.UserCoupon;

@Mapper
@Repository
public interface CouponRepository {

	List<Coupon> getAllCoupons();

	Optional<Coupon> findCouponById(long id);

	Optional<UserCoupon> findUserCouponById(long couponId, long userId);

	int getMaxCouponCount(long id);

	int getIssuedCount(long couponId, long userId);

	void insertUserCoupon(UserCoupon userCoupon);

	void updateIssuedCount(long couponId, long userId, int issuedCount);

	void updateUseCount(long userId, long couponId, int useCount);
}
