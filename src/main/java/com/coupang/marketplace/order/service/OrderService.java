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
import com.coupang.marketplace.order.controller.dto.OrderRequestDto;
import com.coupang.marketplace.payment.controller.dto.PaymentDto;
import com.coupang.marketplace.order.domain.Order;
import com.coupang.marketplace.order.domain.OrderProduct;
import com.coupang.marketplace.payment.domain.Payment;
import com.coupang.marketplace.order.repository.OrderRepository;
import com.coupang.marketplace.payment.service.PaymentService;
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
	private final OrderRepository orderRepository;
	private final PaymentService paymentService;

	@Transactional(readOnly = true)
	@Cacheable(key = "#userId", value = CacheKey.ORDER_INFO)
	public OrderInfo getOrderInfo(long userId){
		List<Cart> cartProducts = cartRepository.findByUserId(userId);
		User userInfo = userRepository.findById(userId);
		String receiverAddress = addressRepository.findMainContentByUserId(userId);
		List<OrderProductInfo> orderProducts = getOrderProductInfo(cartProducts);
		long totalProductPrice = getTotalProductPrice(cartProducts);
		long totalDeliveryFee = getTotalDeliveryFee(cartProducts);

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

	@Transactional
	public void order(long userId, OrderRequestDto orderRequestDto){
		List<Cart> cartProducts = cartRepository.findByUserId(userId);
		long totalProductPrice = getTotalProductPrice(cartProducts);

		PaymentDto paymentDto = PaymentDto.builder()
			.type(orderRequestDto.getType())
			.totalPrice(totalProductPrice)
			.build();

		if(!paymentService.pay(paymentDto))
			throw new IllegalArgumentException("결제에 실패하였습니다.");

		Payment payment = Payment.builder()
			.type(orderRequestDto.getType())
			.totalPrice(totalProductPrice)
			.status(true)
			.discountPrice(0)
			.build();
		long paymentId = paymentService.savePaymentInfo(payment);

		long orderId = saveOrderInfo(orderRequestDto, paymentId);//항상 1이 나옴

		List<OrderProduct> orderProducts = getOrderProduct(orderId, cartProducts);
		saveOrderProducts(orderProducts);

		deleteCartProducts(cartProducts);
	}

	public long getTotalProductPrice(List<Cart> cartProducts){
		return cartProducts.stream().mapToLong(cartProduct -> productRepository.findByProductId(cartProduct.getProductId()).get().getPrice()).sum();
	}

	public long getTotalDeliveryFee(List<Cart> cartProducts){
		return cartProducts.stream().mapToLong(cartProduct -> productRepository.findByProductId(cartProduct.getProductId()).get().getDeliveryFee().longValue()).sum();
	}

	public List<OrderProductInfo> getOrderProductInfo(List<Cart> cartProducts){
		return cartProducts.stream()
			.map((cartProduct) -> OrderService.toOrderProductInfoResponse(cartProduct, productRepository.findByProductId(cartProduct.getProductId()).get().getName(), productRepository.findByProductId(cartProduct.getProductId()).get().getDeliveryFee()))
			.collect(Collectors.toList());
	}

	public static OrderProductInfo toOrderProductInfoResponse(Cart cart, String productName, BigInteger productDeliveryFee){
		return OrderProductInfo.builder()
			.productId(cart.getProductId())
			.productName(productName)
			.productNum(cart.getProductNum())
			.deliveryFee(productDeliveryFee)
			.build();
	}

	public List<OrderProduct> getOrderProduct(long orderId, List<Cart> cartProducts){
		return cartProducts.stream()
			.map((cartProduct) -> OrderService.toOrderProductResponse(orderId, cartProduct))
			.collect(Collectors.toList());
	}

	public static OrderProduct toOrderProductResponse(long orderId, Cart cartProduct){
		return OrderProduct.builder()
			.orderId(orderId)
			.productId(cartProduct.getProductId())
			.productNum(cartProduct.getProductNum())
			.build();
	}

	public long saveOrderInfo(OrderRequestDto dto, long paymentId){
		User userInfo = userRepository.findById(dto.getUserId());
		String receiverAddress = addressRepository.findMainContentByUserId(dto.getUserId());

		Order order = Order.builder()
			.userId(dto.getUserId())
			.consumerName(userInfo.getName())
			.consumerPhone(userInfo.getPhone())
			.receiverName(userInfo.getName())
			.receiverAddress(receiverAddress)
			.receiverPhone(userInfo.getPhone())
			.status(false)
			.paymentId(paymentId)
			.receiverRequest("test")
			.build();

		return orderRepository.insertOrderInfo(order);
	}

	public void saveOrderProducts(List<OrderProduct> orderProducts){
		orderProducts.stream()
			.forEach(orderProduct -> orderRepository.insertOrderProducts(orderProduct));
	}

	public void deleteCartProducts(List<Cart> cartProducts){
		cartProducts.stream()
			.forEach(cartProduct -> cartRepository.deleteCartProducts(cartProduct));
	}
}