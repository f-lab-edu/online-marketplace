package com.coupang.marketplace.order.controller.dto;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	public OrderProductInfo(@JsonProperty("productId") long productId, @JsonProperty("productName") String productName, @JsonProperty("productNum") int productNum, @JsonProperty("deliveryFee") BigInteger deliveryFee){
		this.productId = productId;
		this.productName = productName;
		this.productNum = productNum;
		this.deliveryFee = deliveryFee;
	}
}