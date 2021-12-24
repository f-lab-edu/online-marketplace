package com.coupang.marketplace.order.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.coupang.marketplace.order.domain.Order;
import com.coupang.marketplace.order.domain.OrderProduct;

@Mapper
@Repository
public interface OrderRepository {

	long insertOrderInfo(Order order);

	void insertOrderProducts(OrderProduct orderProduct);
}
