package com.coupang.marketplace.order.controller.dto;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProductInfo {

	@NotNull
	private long productId;

	@NotNull
	private String productName;

	@NotNull
	private int productNum;

	@NotNull
	private BigInteger deliveryFee;

	@Builder
	public OrderProductInfo(long productId, String productName, int productNum, BigInteger deliveryFee){
		this.productId = productId;
		this.productName = productName;
		this.productNum = productNum;
		this.deliveryFee = deliveryFee;
	}
}