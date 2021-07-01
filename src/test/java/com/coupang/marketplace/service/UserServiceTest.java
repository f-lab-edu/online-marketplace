package com.coupang.marketplace.service;

import com.coupang.marketplace.controller.SignUpRequestDto;
import com.coupang.marketplace.domain.User;
import com.coupang.marketplace.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.coupang.marketplace.fixture.UserFixture.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

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

        // when
        assertThrows(IllegalArgumentException.class, () -> userService.join(dto));
    }
}