package com.coupang.marketplace.coupon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coupang.marketplace.coupon.domain.Coupon;
import com.coupang.marketplace.coupon.domain.UserCoupon;
import com.coupang.marketplace.coupon.repository.CouponRepository;
import com.coupang.marketplace.fixture.CouponFixture.*;
import com.coupang.marketplace.fixture.UserCouponFixture.*;
import com.coupang.marketplace.global.fixture.UserFixture;
import com.coupang.marketplace.global.util.session.HttpSessionUtil;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

	@InjectMocks
	private CouponService couponService;

	@Mock
	private CouponRepository couponRepository;

	@Mock
	private HttpSessionUtil httpSessionUtil;

	@DisplayName("만료시간이 지나지 않은 쿠폰을 모두 가져온다.")
	@Test
	public void getCouponsBeforeExpirationTime() {
		//given
		given(couponRepository.getCouponsBeforeExpirationTime())
			.willReturn(Arrays.asList(Coupon1.COUPON));

		//then
		assertThat(couponService.getAvailableCoupons().size()).isEqualTo(1);
	}

	@DisplayName("만료시간이 지나지 않은 쿠폰이면 저장에 성공한다.")
	@Test
	public void saveCouponAfterExpirationTime() {
		//given
		final Optional<Coupon> FoundAvailableCoupon = Optional.ofNullable(Coupon1.COUPON);
		given(couponRepository.findAvailableCouponId(Coupon1.ID)).willReturn(FoundAvailableCoupon);

		final Optional<UserCoupon> notFoundUserCoupon = Optional.ofNullable(null);
		given(couponRepository.findByCouponId(Coupon1.ID)).willReturn(notFoundUserCoupon);

		given((Long)httpSessionUtil.getAttribute()).willReturn(UserFixture.User1.ID);

		//when
		couponService.saveCoupon(Coupon1.ID);

		//then
		then(couponRepository).should(times(1)).insertUserCoupon(any());
	}

	@DisplayName("만료시간이 지난 쿠폰은 저장에 실패한다.")
	@Test
	public void saveCouponBeforeExpirationTime() {
		//when
		couponRepository.findAvailableCouponId(Coupon3.ID);

		//then
		assertThrows(IllegalArgumentException.class, () -> couponService.saveCoupon(Coupon3.ID));
	}

	@DisplayName("이미 저장된 쿠폰은 중복저장에 실패한다.")
	@Test
	public void saveCouponAlreadyHave() {
		//given
		final Optional<UserCoupon> FoundUserCoupon = Optional.ofNullable(UserCoupon1.USERCOUPON);
		given(couponRepository.findByCouponId(Coupon1.ID)).willReturn(FoundUserCoupon);

		//when
		couponRepository.findByCouponId(Coupon1.ID);

		//then
		assertThrows(IllegalArgumentException.class, () -> couponService.saveCoupon(Coupon1.ID));
	}
}