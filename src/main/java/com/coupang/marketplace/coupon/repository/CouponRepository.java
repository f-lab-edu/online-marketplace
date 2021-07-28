package com.coupang.marketplace.coupon.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.coupang.marketplace.coupon.domain.Coupon;

@Mapper
@Repository
public interface CouponRepository {

	List<Coupon> getCouponsBeforeExpirationTime();
}
