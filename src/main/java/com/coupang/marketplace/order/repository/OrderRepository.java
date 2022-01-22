package com.coupang.marketplace.order.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.coupang.marketplace.order.domain.Order;
import com.coupang.marketplace.order.domain.OrderProduct;

import java.util.List;

@Mapper
@Repository
public interface OrderRepository {

	void insertOrderInfo(Order order);

	void insertOrderProducts(OrderProduct orderProduct);

	List<Order> findOrdersByUserId(long userId);
}
