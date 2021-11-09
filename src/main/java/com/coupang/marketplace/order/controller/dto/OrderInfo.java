package com.coupang.marketplace.order.controller.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	public OrderInfo(@JsonProperty("userId") long userId, @JsonProperty("consumerName") String consumerName, @JsonProperty("consumerPhone") String consumerPhone, @JsonProperty("receiverName") String receiverName, @JsonProperty("receiverAddress") String receiverAddress, @JsonProperty("receiverPhone") String receiverPhone, @JsonProperty("receiverRequest") String receiverRequest, @JsonProperty("orderProducts") List<OrderProductInfo> orderProducts, @JsonProperty("productPrice") long productPrice, @JsonProperty("deliveryFee") long deliveryFee){
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