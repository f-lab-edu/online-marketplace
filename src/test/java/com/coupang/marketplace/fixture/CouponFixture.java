package com.coupang.marketplace.fixture;

import java.sql.Timestamp;

import com.coupang.marketplace.coupon.domain.Coupon;

public class CouponFixture {

	public static class Coupon1 {
		public static final Long ID = 1L;
		public static final String NAME = "coupon1";
		public static final Long MIN_PRICE = 30000L;
		public static final Long DISCOUNT_PRICE = 2000L;
		public static final Long PRODUCT_ID = 11L;
		public static final Timestamp EXPIRATION_TIME = Timestamp.valueOf("2021-10-10 12:59:59");

		public static final Coupon COUPON = Coupon.builder()
			.id(ID)
			.name(NAME)
			.minPrice(MIN_PRICE)
			.discountPrice(DISCOUNT_PRICE)
			.productId(PRODUCT_ID)
			.expirationTime(EXPIRATION_TIME)
			.build();
	}
}