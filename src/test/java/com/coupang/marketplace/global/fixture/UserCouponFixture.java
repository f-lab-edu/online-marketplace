package com.coupang.marketplace.global.fixture;


import com.coupang.marketplace.coupon.domain.UserCoupon;

public class UserCouponFixture {

	public static class UserCoupon1 {
		public static final Long ID = 1L;
		public static final Long USER_ID = 1L;
		public static final Long COUPON_ID = 2L;
		public static final boolean USE_STATUS = false;
		public static final int ISSUED_COUPON_COUNT = 1;

		public static final UserCoupon USERCOUPON = UserCoupon.builder()
			.id(ID)
			.userId(USER_ID)
			.couponId(COUPON_ID)
			.useStatus(USE_STATUS)
			.issuedCouponCount(ISSUED_COUPON_COUNT)
			.build();
	}

	public static class UserCoupon2 {
		public static final Long ID = 2L;
		public static final Long USER_ID = 2L;
		public static final Long COUPON_ID = 2L;
		public static final boolean USE_STATUS = false;
		public static final int ISSUED_COUPON_COUNT = 5;

		public static final UserCoupon USERCOUPON = UserCoupon.builder()
			.id(ID)
			.userId(USER_ID)
			.couponId(COUPON_ID)
			.useStatus(USE_STATUS)
			.issuedCouponCount(ISSUED_COUPON_COUNT)
			.build();
	}
}