package com.coupang.marketplace.payment.service;

import org.springframework.stereotype.Service;

import com.coupang.marketplace.payment.controller.dto.PaymentDto;
import com.coupang.marketplace.payment.domain.Payment;
import com.coupang.marketplace.payment.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;

	public boolean pay(PaymentDto paymentDto){
		if(!mockPayment(paymentDto))
			throw new IllegalArgumentException("결제에 실패하였습니다.");
		return true;
	}

	public long savePaymentInfo(Payment payment){
		paymentRepository.insertPaymentInfo(payment);
		return payment.getId();
	}

	public boolean mockPayment(PaymentDto dto){
		return true;
	}
}
