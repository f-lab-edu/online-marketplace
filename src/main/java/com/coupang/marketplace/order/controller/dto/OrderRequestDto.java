package com.coupang.marketplace.order.controller.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderRequestDto {

	@NotNull
	private long userId;

	@NotNull
	private int type;

	@Builder
	public OrderRequestDto(@JsonProperty("userId") long userId, @JsonProperty("type") int type){
		this.userId = userId;
		this.type = type;
	}
}
