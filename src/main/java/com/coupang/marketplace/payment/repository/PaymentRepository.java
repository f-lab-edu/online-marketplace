package com.coupang.marketplace.payment.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.coupang.marketplace.payment.domain.Payment;

@Mapper
@Repository
public interface PaymentRepository {

	long insertPaymentInfo(Payment payment);
}
