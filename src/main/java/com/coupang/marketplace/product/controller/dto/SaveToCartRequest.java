package com.coupang.marketplace.product.controller.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class SaveToCartRequest {

	@NotNull
	private long productId;

	@NotNull
	private int productNum;
}