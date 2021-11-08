package com.coupang.marketplace.order.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProduct {

	private long id;
	private long orderId;
	private long productId;
	private int productNum;

	@Builder
	public OrderProduct(long id, long orderId, long productId, int productNum){
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.productNum = productNum;
	}
}