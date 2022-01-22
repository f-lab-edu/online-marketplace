package com.coupang.marketplace.product.controller.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SaveToCartRequest {

	@NotNull
	private long productId;

	@NotNull
	private int productNum;

	@Builder
	public SaveToCartRequest(long productId, int productNum){
		this.productId = productId;
		this.productNum = productNum;
	}
}