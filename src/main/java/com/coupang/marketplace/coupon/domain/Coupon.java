package com.coupang.marketplace.coupon.domain;

import java.time.ZonedDateTime;

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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private ZonedDateTime expirationTime;

	@Builder
	public Coupon(Long id, String name, Long minPrice, Long discountPrice, Long productId, ZonedDateTime expirationTime){
		this.id = id;
		this.name = name;
		this.minPrice = minPrice;
		this.discountPrice = discountPrice;
		this.productId = productId;
		this.expirationTime = expirationTime;
	}
}
