package com.coupang.marketplace.cart.domain;

import java.math.BigInteger;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Cart {

	private BigInteger id;
	private BigInteger productId;
	private int productNum;
	private BigInteger userId;

	@Builder
	public Cart(BigInteger id, BigInteger productId, int productNum, BigInteger userId){
		this.id = id;
		this.productId = productId;
		this.productNum = productNum;
		this.userId = userId;
	}
}