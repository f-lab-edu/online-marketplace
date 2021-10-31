package com.coupang.marketplace.coupon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coupang.marketplace.coupon.repository.CouponRepository;
import com.coupang.marketplace.global.fixture.CouponFixture.*;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

	@InjectMocks
	private CouponService couponService;

	@Mock
	private CouponRepository couponRepository;

	@DisplayName("만료시간이 지나지 않은 쿠폰을 모두 가져온다.")
	@Test
	public void getCouponsBeforeExpirationTime() {
		//given
		given(couponRepository.getCouponsBeforeExpirationTime())
			.willReturn(Arrays.asList(Coupon1.COUPON));

		//then
		assertThat(couponService.getAvailableCoupons().size()).isEqualTo(1);
	}
}