package com.coupang.marketplace.coupon.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import com.coupang.marketplace.global.template.ControllerTestTemplate;

public class CouponControllerTest extends ControllerTestTemplate {

	@DisplayName("이벤트/쿠폰을 선택하면 사용 가능한 쿠폰 목록을 보여준다.")
	@Test
	public void getAvailableCoupons() throws Exception{
		//when
		final ResultActions actions = mvc.perform(get("/coupons"))
			.andDo(print());

		//then
		actions
			.andExpect(status().isOk())
			.andDo(print());
	}
}