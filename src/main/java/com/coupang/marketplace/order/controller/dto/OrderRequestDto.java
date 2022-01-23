package com.coupang.marketplace.order.controller.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.coupang.marketplace.payment.controller.dto.PaymentType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderRequestDto {

	@NotNull
	private PaymentType type;

	@NotNull
	private String receiverName;

	@NotNull
	private String receiverPhone;

	@Max(50)
	@NotNull
	private String receiverRequest;

	@Builder
	public OrderRequestDto(PaymentType type, String receiverName, String receiverPhone, String receiverRequest){
		this.type = type;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.receiverRequest = receiverRequest;
	}
}
