package com.coupang.marketplace.order.service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupang.marketplace.address.repository.AddressRepository;
import com.coupang.marketplace.cart.domain.Cart;
import com.coupang.marketplace.cart.repository.CartRepository;
import com.coupang.marketplace.global.constant.CacheKey;
import com.coupang.marketplace.order.controller.dto.OrderInfo;
import com.coupang.marketplace.order.controller.dto.OrderProductInfo;
import com.coupang.marketplace.product.repository.ProductRepository;
import com.coupang.marketplace.user.domain.User;
import com.coupang.marketplace.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {

	private final CartRepository cartRepository;
	private final UserRepository userRepository;
	private final AddressRepository addressRepository;
	private final ProductRepository productRepository;

	@Transactional(readOnly = true)
	@Cacheable(key = "#userId", value = CacheKey.ORDER_INFO)
	public OrderInfo getOrderInfo(long userId){
		List<Cart> cartProducts = cartRepository.findByUserId(userId);
		User userInfo = userRepository.findById(userId);
		String receiverAddress = addressRepository.findMainContentByUserId(userId);
		List<OrderProductInfo> orderProducts = getOrderProductInfo(cartProducts);
		long totalProductPrice = totalProductPrice(cartProducts);
		long totalDeliveryFee = totalDeliveryFee(cartProducts);

		OrderInfo orderInfo = OrderInfo.builder()
			.userId(userId)
			.consumerName(userInfo.getName())
			.consumerPhone(userInfo.getPhone())
			.receiverName(userInfo.getName())
			.receiverAddress(receiverAddress)
			.receiverPhone(userInfo.getPhone())
			.orderProducts(orderProducts)
			.productPrice(totalProductPrice)
			.deliveryFee(totalDeliveryFee)
			.build();
		return orderInfo;
	}

	public long totalProductPrice(List<Cart> cartProducts){
		return cartProducts.stream().mapToLong(cartProduct -> productRepository.findByProductId(cartProduct.getProductId()).get().getPrice()).sum();
	}

	public long totalDeliveryFee(List<Cart> cartProducts){
		return cartProducts.stream().mapToLong(cartProduct -> productRepository.findByProductId(cartProduct.getProductId()).get().getDeliveryFee().longValue()).sum();
	}

	public List<OrderProductInfo> getOrderProductInfo(List<Cart> cartProducts){
		return cartProducts.stream()
			.map((cartProduct) -> OrderService.toResponse(cartProduct, productRepository.findByProductId(cartProduct.getProductId()).get().getName(), productRepository.findByProductId(cartProduct.getProductId()).get().getDeliveryFee()))
			.collect(Collectors.toList());
	}

	public static OrderProductInfo toResponse(Cart cart, String productName, BigInteger productDeliveryFee){
		return OrderProductInfo.builder()
			.productId(cart.getProductId())
			.productName(productName)
			.productNum(cart.getProductNum())
			.deliveryFee(productDeliveryFee)
			.build();
	}
}