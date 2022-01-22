package com.coupang.marketplace.global.fixture;

import com.coupang.marketplace.cart.domain.Cart;

public class CartFixture {

	public static class Cart1{
		public static final long ID = 1L;
		public static final long PRODUCT_ID = 1L;
		public static final int PRODUCT_NUM = 3;
		public static final long USER_ID = 1L;

		public static final Cart CART = Cart.builder()
			.id(ID)
			.productId(PRODUCT_ID)
			.productNum(PRODUCT_NUM)
			.userId(USER_ID)
			.build();
	}

	public static class Cart2{
		public static final long ID = 2L;
		public static final long PRODUCT_ID = 1L;
		public static final int PRODUCT_NUM = 1;
		public static final long USER_ID = 2L;

		public static final Cart CART = Cart.builder()
				.id(ID)
				.productId(PRODUCT_ID)
				.productNum(PRODUCT_NUM)
				.userId(USER_ID)
				.build();
	}
}