package com.coupang.marketplace.product.controller.dto;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class SaveToCartRequest {

	@NotNull
	private BigInteger productId;

	@NotNull
	private int productNum;
}