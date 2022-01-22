package com.coupang.marketplace.cart.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Cart {

	private long id;
	private long productId;
	private int productNum;
	private long userId;

	@Builder
	public Cart(long id, long productId, int productNum, long userId){
		this.id = id;
		this.productId = productId;
		this.productNum = productNum;
		this.userId = userId;
	}
}