package com.coupang.marketplace.service;

import com.coupang.marketplace.controller.SignUpRequestDto;
import com.coupang.marketplace.domain.User;
import com.coupang.marketplace.repository.UserRepository;
import com.coupang.marketplace.util.SaltGenerator;
import com.coupang.marketplace.util.Sha256Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SaltGenerator saltGenerator;
    private final Sha256Encryptor sha256Encryptor;

    public void join(SignUpRequestDto dto){
        if (checkIsUserExist(dto.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 메일입니다.");
        }

        String salt = saltGenerator.run();
        String encryptedPassword = sha256Encryptor.run(dto.getPassword(), salt);
        User user = dto.toEntity(salt, encryptedPassword);

        userRepository.insertUser(user);
    }

    public boolean checkIsUserExist (String email) {
        return userRepository.findByEmail(email).isPresent();
    }


}
