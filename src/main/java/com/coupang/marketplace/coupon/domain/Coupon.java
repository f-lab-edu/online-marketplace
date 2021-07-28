package com.coupang.marketplace.coupon.domain;

import java.math.BigInteger;
import java.sql.Timestamp;

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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp expirationTime;

	@Builder
	public Coupon(BigInteger id, String name, BigInteger minPrice, BigInteger discountPrice, BigInteger productId, Timestamp expirationTime){
		this.id = id;
		this.name = name;
		this.minPrice = minPrice;
		this.discountPrice = discountPrice;
		this.productId = productId;
		this.expirationTime = expirationTime;
	}
}
