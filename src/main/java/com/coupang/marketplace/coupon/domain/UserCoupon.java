package com.coupang.marketplace.coupon.domain;


import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCoupon {

	private Long id;

	private Long userId;

	private Long couponId;

	private int useCount;

	private int issuedCount;

	@Builder
	public UserCoupon(Long id, Long userId, Long couponId, int useCount, int issuedCount){
		this.id = id;
		this.userId = userId;
		this.couponId = couponId;
		this.useCount = useCount;
		this.issuedCount = issuedCount;
	}
}