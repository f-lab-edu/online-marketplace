package com.coupang.marketplace.coupon.domain;


import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCoupon {

	private Long id;

	private Long userId;

	private Long couponId;

	private boolean useStatus;

	@Builder
	public UserCoupon(Long id, Long userId, Long couponId, boolean useStatus){
		this.id = id;
		this.userId = userId;
		this.couponId = couponId;
		this.useStatus = useStatus;
	}
}