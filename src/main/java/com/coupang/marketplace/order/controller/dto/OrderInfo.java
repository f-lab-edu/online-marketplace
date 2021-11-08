package com.coupang.marketplace.order.controller.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Builder;
import lombok.Getter;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OrderInfo {

	@NotNull
	private long userId;

	@NotNull
	private String consumerName;

	@NotNull
	private String consumerPhone;

	@NotNull
	private String receiverName;

	@NotNull
	private String receiverAddress;

	@NotNull
	private String receiverPhone;

	private String receiverRequest;

	@NotNull
	private List<OrderProductInfo> orderProducts;

	@NotNull
	private long productPrice;

	private long deliveryFee;

	@Builder
	public OrderInfo(long userId, String consumerName, String consumerPhone, String receiverName, String receiverAddress, String receiverPhone, String receiverRequest, List<OrderProductInfo> orderProducts, long productPrice, long deliveryFee){
		this.userId = userId;
		this.consumerName = consumerName;
		this.consumerPhone = consumerPhone;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.receiverPhone = receiverPhone;
		this.receiverRequest = receiverRequest;
		this.orderProducts = orderProducts;
		this.productPrice = productPrice;
		this.deliveryFee = deliveryFee;
	}
}