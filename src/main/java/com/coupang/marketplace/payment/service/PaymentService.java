package com.coupang.marketplace.payment.service;

import org.springframework.stereotype.Service;

import com.coupang.marketplace.auth.AuthRequired;
import com.coupang.marketplace.payment.controller.dto.PaymentDto;
import com.coupang.marketplace.payment.domain.Payment;
import com.coupang.marketplace.payment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;

	@AuthRequired
	public boolean pay(PaymentDto dto){
		if(!mockPayment(dto))
			throw new IllegalArgumentException("결제에 실패하였습니다.");
		return true;
	}

	public long savePaymentInfo(Payment payment){
		return paymentRepository.insertPaymentInfo(payment);
	}

	public boolean mockPayment(PaymentDto dto){
		return true;
	}
}
