package com.coupang.marketplace.global.fixture;


import com.coupang.marketplace.coupon.domain.UserCoupon;

public class UserCouponFixture {

	public static class UserCoupon1 {
		public static final Long ID = 1L;
		public static final Long USER_ID = 1L;
		public static final Long COUPON_ID = 2L;
		public static final int USE_COUNT = 0;
		public static final int ISSUED_COUNT = 1;

		public static final UserCoupon USERCOUPON = UserCoupon.builder()
			.id(ID)
			.userId(USER_ID)
			.couponId(COUPON_ID)
			.useCount(USE_COUNT)
			.issuedCount(ISSUED_COUNT)
			.build();
	}

	public static class UserCoupon2 {
		public static final Long ID = 2L;
		public static final Long USER_ID = 2L;
		public static final Long COUPON_ID = 2L;
		public static final int USE_COUNT = 0;
		public static final int ISSUED_COUNT = 5;

		public static final UserCoupon USERCOUPON = UserCoupon.builder()
			.id(ID)
			.userId(USER_ID)
			.couponId(COUPON_ID)
			.useCount(USE_COUNT)
			.issuedCount(ISSUED_COUNT)
			.build();
	}
}