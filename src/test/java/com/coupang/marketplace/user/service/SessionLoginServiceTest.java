package com.coupang.marketplace.user.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coupang.marketplace.user.controller.dto.SignInRequestDto;
import com.coupang.marketplace.user.domain.User;
import com.coupang.marketplace.global.fixture.UserFixture.*;
import com.coupang.marketplace.user.repository.UserRepository;
import com.coupang.marketplace.global.util.crypto.Encryptor;

@ExtendWith(MockitoExtension.class)
public class SessionLoginServiceTest {

	@InjectMocks
	private SessionLoginService sessionLoginService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private Encryptor encryptor;

	@Mock
	private HttpSession httpSession;

	@DisplayName("이메일이 존재하고 패스워드가 일치하면 로그인에 성공한다.")
	@Test
	void signIn() {
		// given
		final SignInRequestDto dto = SignInRequestDto.builder()
			.email(User1.EMAIL)
			.password(User1.PASSWORD)
			.build();
		final Optional<User> user = Optional.of(User1.USER);
		final String encryptedPassword = User1.ENCRYPTED_PASSWORD;

		given(userRepository.findByEmail(dto.getEmail())).willReturn(user);
		given(encryptor.encrypt(any())).willReturn(encryptedPassword);

		// when
		sessionLoginService.login(dto);

		// then
		then(httpSession).should(times(1)).setAttribute(any(),any());
	}

	@DisplayName("존재하지 않은 이메일이면 에러를 내보낸다.")
	@Test
	void signInWithNotFoundEmail() {
		//given
		final SignInRequestDto dto = SignInRequestDto.builder()
			.email(User1.EMAIL)
			.password(User1.PASSWORD)
			.build();
		final Optional<User> notFoundUser = Optional.ofNullable(null);

		given(userRepository.findByEmail(any())).willReturn(notFoundUser);

		// then
		assertThrows(IllegalArgumentException.class, () -> sessionLoginService.login(dto));
	}

	@DisplayName("이메일이 존재하지만 패스워드가 일치하지 않으면 에러를 내보낸다.")
	@Test
	void signInWithInvalidPassword() {
		// given
		final SignInRequestDto dto = SignInRequestDto.builder()
			.email(User1.EMAIL)
			.password(User2.PASSWORD)
			.build();
		final Optional<User> user = Optional.of(User1.USER);
		final String encryptedPassword = User2.ENCRYPTED_PASSWORD;

		given(userRepository.findByEmail(any())).willReturn(user);
		given(encryptor.encrypt(any())).willReturn(encryptedPassword);

		// then
		assertThrows(IllegalArgumentException.class, () -> sessionLoginService.login(dto));
	}
}
