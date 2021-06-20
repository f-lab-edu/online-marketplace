package com.coupang.marketplace.service;

import com.coupang.marketplace.controller.SignUpRequestDto;
import com.coupang.marketplace.domain.User;
import com.coupang.marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void save(SignUpRequestDto requestDto){
        User user = requestDto.toEntity();
        userRepository.insertUser(user);
    }
}
