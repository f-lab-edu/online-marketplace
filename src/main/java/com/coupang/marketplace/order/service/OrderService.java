package com.coupang.marketplace.order.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.coupang.marketplace.coupon.service.CouponService;
import com.coupang.marketplace.payment.domain.Payment;
import com.coupang.marketplace.payment.service.PaymentService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupang.marketplace.address.repository.AddressRepository;
import com.coupang.marketplace.cart.domain.Cart;
import com.coupang.marketplace.cart.repository.CartRepository;
import com.coupang.marketplace.global.constant.CacheKey;
import com.coupang.marketplace.order.controller.dto.OrderRequestDto;
import com.coupang.marketplace.payment.controller.dto.PaymentDto;
import com.coupang.marketplace.order.domain.Order;
import com.coupang.marketplace.order.domain.OrderProduct;
import com.coupang.marketplace.order.repository.OrderRepository;
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
	private final CouponService couponService;

	@Transactional(readOnly = true)
	@Cacheable(key = "#userId", value = CacheKey.ORDER_INFO)
	public List<Order> getOrderInfo(long userId){

		List<Order> orderBills = orderRepository.findOrdersByUserId(userId);

		return orderBills;
	}

	@Transactional
	public void order(long userId, OrderRequestDto orderRequestDto){
		List<Cart> cartProducts = cartRepository.findByUserId(userId);
		long totalPrice = getTotalProductPrice(cartProducts) + getTotalDeliveryFee(cartProducts);
		Optional<Long> couponId = orderRequestDto.getCouponId();

		if(totalPrice == 0)
			throw new IllegalArgumentException("주문을 요청한 상품이 없습니다.");

		long discountPrice = couponService.getDiscountPriceByCoupon(userId, couponId, cartProducts, getTotalProductPrice(cartProducts));

		PaymentDto paymentDto = PaymentDto.builder()
			.type(orderRequestDto.getType())
			.totalPrice(totalPrice - discountPrice)
			.build();

		try {
			paymentService.pay(paymentDto);
		}catch(Exception e) {
			throw new IllegalArgumentException("결제에 실패하였습니다.", e);
		}

		Payment payment = Payment.builder()
				.type(paymentDto.getType().ordinal())
				.totalPrice(paymentDto.getTotalPrice())
				.status(true)
				.discountPrice(discountPrice)
				.build();
		long paymentId = paymentService.savePaymentInfo(payment);
		couponService.IncreaseUseCount(userId, couponId.get());

		long orderId = saveOrderInfo(userId, paymentId, orderRequestDto);

		List<OrderProduct> orderProducts = getOrderProduct(orderId, cartProducts);
		saveOrderProducts(orderProducts);

		deleteCartProducts(cartProducts);
	}

	public long getTotalProductPrice(List<Cart> cartProducts){
		return cartProducts.stream().mapToLong(cartProduct -> productRepository.findByProductId(cartProduct.getProductId()).get().getPrice() * cartProduct.getProductNum()).sum();
	}

	public long getTotalDeliveryFee(List<Cart> cartProducts){
		return cartProducts.stream().mapToLong(cartProduct -> productRepository.findByProductId(cartProduct.getProductId()).get().getDeliveryFee().longValue()).sum();
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

	public long saveOrderInfo(long userId, long paymentId, OrderRequestDto dto){
		User userInfo = userRepository.findById(userId);
		String receiverAddress = addressRepository.findMainContentByUserId(userId);

		Order order = Order.builder()
			.userId(userId)
			.consumerName(userInfo.getName())
			.consumerPhone(userInfo.getPhone())
			.receiverName(dto.getReceiverName())
			.receiverAddress(receiverAddress)
			.receiverPhone(dto.getReceiverPhone())
			.status(false)
			.paymentId(paymentId)
			.receiverRequest(dto.getReceiverRequest())
			.build();

		orderRepository.insertOrderInfo(order);
		return order.getId();
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