package com.coupang.marketplace.service;

import com.coupang.marketplace.controller.SignUpRequestDto;
import com.coupang.marketplace.domain.User;
import com.coupang.marketplace.repository.UserRepository;
import com.coupang.marketplace.util.Encryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.coupang.marketplace.fixture.UserFixture.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Encryptor encryptor;

    @DisplayName("중복되지 않은 이메일이면 회원가입에 성공한다.")
    @Test
    void signUp() {
        // given
        final SignUpRequestDto dto = SignUpRequestDto.builder()
                .name(User1.NAME)
                .email(User1.EMAIL)
                .password(User1.PASSWORD)
                .phone(User1.PHONE)
                .build();
        final Optional<User> notFoundUser = Optional.ofNullable(null);

        given(userRepository.findByEmail(any())).willReturn(notFoundUser);

        // when
        userService.join(dto);

        // then
        then(userRepository).should(times(1)).insertUser(any());

    }

    @DisplayName("중복된 이메일이면 에러를 내보낸다.")
    @Test
    void signUpWithDuplicateEmail() {
        // given
        final SignUpRequestDto dto = SignUpRequestDto.builder()
            .name(User1.NAME)
            .email(User1.EMAIL)
            .password(User1.PASSWORD)
            .phone(User1.PHONE)
            .build();
        final Optional<User> user = Optional.of(User1.USER);

        given(userRepository.findByEmail(any())).willReturn(user);

        // then
        assertThrows(IllegalArgumentException.class, () -> userService.join(dto));
    }
}