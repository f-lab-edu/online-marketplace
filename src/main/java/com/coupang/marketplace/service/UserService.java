package com.coupang.marketplace.service;

import com.coupang.marketplace.controller.SignUpRequestDto;
import com.coupang.marketplace.domain.User;
import com.coupang.marketplace.repository.UserRepository;
import com.coupang.marketplace.util.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void save(SignUpRequestDto dto){
        if (checkIfUserExist(dto.getEmail())) {
            throw new Error("이미 등록된 이메일입니다.");
        }

        String salt = CryptoUtil.generateSalt();
        String encryptedPassword = CryptoUtil.encryptPassword(dto.getPassword(), salt);
        User user = dto.toEntity(salt, encryptedPassword);

        userRepository.insertUser(user);
    }

    public boolean checkIfUserExist (String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
