package com.coupang.marketplace.payment.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PaymentDto {

	@NotNull
	private int type;

	private long discountPrice;

	@NotNull
	private long totalPrice;

	@Builder
	public PaymentDto(int type, long discountPrice, long totalPrice){
		this.type = type;
		this.discountPrice = discountPrice;
		this.totalPrice = totalPrice;
	}
}
