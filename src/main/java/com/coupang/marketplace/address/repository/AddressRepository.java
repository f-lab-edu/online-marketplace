package com.coupang.marketplace.address.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AddressRepository {

	String findMainContentByUserId(long userId);
}