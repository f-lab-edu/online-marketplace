package com.coupang.marketplace.fixture;

import java.math.BigInteger;
import java.sql.Timestamp;

import com.coupang.marketplace.coupon.domain.Coupon;

public class CouponFixture {

	public static class Coupon1 {
		public static final BigInteger ID = new BigInteger("1");
		public static final String NAME = "coupon1";
		public static final BigInteger MIN_PRICE = new BigInteger("30000");
		public static final BigInteger DISCOUNT_PRICE = new BigInteger("2000");
		public static final BigInteger PRODUCT_ID = new BigInteger("11");
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