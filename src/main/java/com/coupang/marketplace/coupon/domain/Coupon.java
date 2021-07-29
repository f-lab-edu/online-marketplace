package com.coupang.marketplace.coupon.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Coupon {

	private Long id;

	private String name;

	private Long minPrice;

	private Long discountPrice;

	private Long productId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp expirationTime;

	@Builder
	public Coupon(Long id, String name, Long minPrice, Long discountPrice, Long productId, Timestamp expirationTime){
		this.id = id;
		this.name = name;
		this.minPrice = minPrice;
		this.discountPrice = discountPrice;
		this.productId = productId;
		this.expirationTime = expirationTime;
	}
}
