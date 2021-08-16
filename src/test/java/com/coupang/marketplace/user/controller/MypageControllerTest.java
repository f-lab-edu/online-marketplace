package com.coupang.marketplace.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.ResultActions;

import com.coupang.marketplace.fixture.UserFixture.*;
import com.coupang.marketplace.global.constant.SessionKey;
import com.coupang.marketplace.user.controller.dto.UpdateRequestDto;

public class MypageControllerTest extends ControllerTest {

	@DisplayName("로그인 검증이 된 사용자는 회원 정보 수정에 성공한다.")
	@Test
	void updateUser() throws Exception {
		// given
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(SessionKey.LOGIN_USER_ID, User1.ID);

		final UpdateRequestDto dto = UpdateRequestDto.builder()
			.name(User2.NAME)
			.password(User2.PASSWORD)
			.phone(User2.PHONE)
			.build();

		// when
		final ResultActions actions = mvc.perform(put("/mypage/my-info")
			.session(session)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(dto)))
			.andDo(print());

		// then
		actions
			.andExpect(status().isOk())
			.andDo(print());
	}

	@DisplayName("로그인 검증되지 않은 사용자는 회원 정보 수정에 실패한다.")
	@Test
	void updateUserWithNoAuthenticated() throws Exception {
		// given
		final UpdateRequestDto dto = UpdateRequestDto.builder()
			.name(User2.NAME)
			.password(User2.PASSWORD)
			.phone(User2.PHONE)
			.build();

		// when
		final ResultActions actions = mvc.perform(put("/mypage/my-info")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(dto)))
			.andDo(print());

		// then
		actions
			.andExpect(status().isUnauthorized())
			.andDo(print());
	}
}
