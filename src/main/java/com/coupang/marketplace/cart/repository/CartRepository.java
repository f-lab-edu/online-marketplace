package com.coupang.marketplace.cart.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.coupang.marketplace.cart.domain.Cart;

@Mapper
@Repository
public interface CartRepository {

	List<Cart> findByUserId(long userId);

	void insertProduct(Cart cart);

	void updateProductNum(long productId, int productNum);

	Optional<Cart> findByProductId(long userId, long productId);
}