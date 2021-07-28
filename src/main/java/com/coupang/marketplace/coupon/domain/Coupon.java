package com.coupang.marketplace.coupon.domain;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Coupon {

	private BigInteger id;

	private String name;

	private BigInteger minPrice;

	private BigInteger discountPrice;

	private BigInteger productId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime expirationTime;

	@Builder
	public Coupon(BigInteger id, String name, BigInteger minPrice, BigInteger discountPrice, BigInteger productId, LocalDateTime expirationTime){
		this.id = id;
		this.name = name;
		this.minPrice = minPrice;
		this.discountPrice = discountPrice;
		this.productId = productId;
		this.expirationTime = expirationTime;
	}
}
