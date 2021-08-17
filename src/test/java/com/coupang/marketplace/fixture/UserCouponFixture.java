package com.coupang.marketplace.fixture;


import com.coupang.marketplace.coupon.domain.UserCoupon;

public class UserCouponFixture {

	public static class UserCoupon1 {
		public static final Long ID = 1L;
		public static final Long USERID = 30000L;
		public static final Long COUPONID = 2000L;
		public static final boolean USESTATUS = false;

		public static final UserCoupon USERCOUPON = UserCoupon.builder()
			.id(ID)
			.userId(USERID)
			.couponId(COUPONID)
			.useStatus(USESTATUS)
			.build();
	}
}