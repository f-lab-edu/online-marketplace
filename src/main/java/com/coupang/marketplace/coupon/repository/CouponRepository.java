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

	List<Coupon> getCouponsBeforeExpirationTime();

	Optional<Coupon> findAvailableCouponId(long id);

	Optional<UserCoupon> findByCouponId(long id);

	void insertUserCoupon(UserCoupon userCoupon);
}
