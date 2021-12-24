package com.coupang.marketplace.payment.domain;

import java.time.ZonedDateTime;

import com.coupang.marketplace.payment.controller.dto.PaymentType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Payment {

	private long id;
	private int type;
	private long discountPrice;
	private long totalPrice;
	private boolean status;
	private ZonedDateTime createdAt;

	@Builder
	public Payment(long id, int type, long discountPrice, long totalPrice, boolean status, ZonedDateTime createdAt){
		this.id = id;
		this.type = type;
		this.discountPrice = discountPrice;
		this.totalPrice = totalPrice;
		this.status = status;
		this.createdAt = createdAt;
	}
}
