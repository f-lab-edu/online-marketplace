package com.coupang.marketplace.service;

import com.coupang.marketplace.controller.SignInRequestDto;
import com.coupang.marketplace.controller.SignUpRequestDto;
import com.coupang.marketplace.domain.User;
import com.coupang.marketplace.repository.UserRepository;
import com.coupang.marketplace.util.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void save(SignUpRequestDto dto){
        if (checkIfUserExist(dto.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 메일입니다.");
        }

        String salt = CryptoUtil.generateSalt();
        String encryptedPassword = CryptoUtil.encryptPassword(dto.getPassword(), salt);
        User user = dto.toEntity(salt, encryptedPassword);

        userRepository.insertUser(user);
    }

    public boolean checkIfUserExist (String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Optional<User> login(SignInRequestDto dto){
        if (!checkIfUserExist(dto.getEmail())) {
            throw new Error("존재하지 않는 이메일입니다.");
        }
        else {
            Optional<User> user = userRepository.findByEmail(dto.getEmail());

            String email = dto.getEmail();
            String password = dto.getPassword();

            //TO DO : 암호화된 비밀번호와 일치하는지 확인

            //TO DO : 실패

            //성공
            return user;
        }
    }
}
