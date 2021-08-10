package com.coupang.marketplace.user.service;

import javax.servlet.http.HttpSession;

import com.coupang.marketplace.global.constant.SessionKey;
import com.coupang.marketplace.global.error.AuthenticationException;
import com.coupang.marketplace.user.controller.dto.SignUpRequestDto;
import com.coupang.marketplace.user.controller.dto.UpdateRequestDto;
import com.coupang.marketplace.user.domain.User;
import com.coupang.marketplace.user.repository.UserRepository;
import com.coupang.marketplace.global.util.crypto.CryptoData;
import com.coupang.marketplace.global.util.crypto.Encryptor;
import com.coupang.marketplace.global.util.crypto.SaltGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    @Qualifier("sha256Encryptor")
    private final Encryptor encryptor;

    public void join(SignUpRequestDto dto){
        if (checkIsUserExist(dto.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 메일입니다.");
        }

        String salt = SaltGenerator.generateSalt();
        CryptoData cryptoData = CryptoData.WithSaltBuilder()
                .plainText(dto.getPassword())
                .salt(salt)
                .build();
        String encryptedPassword = encryptor.encrypt(cryptoData);
        User user = dto.toEntity(salt, encryptedPassword);

        userRepository.insertUser(user);
    }

    public boolean checkIsUserExist (String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void updateUser(Long id, UpdateRequestDto dto){
        HttpSession httpSession = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        if(!httpSession.getAttribute(SessionKey.LOGIN_USER_ID).equals(id))
            throw new AuthenticationException("다른 사용자의 정보에 접근하였습니다.");

        String salt = SaltGenerator.generateSalt();
        CryptoData cryptoData = CryptoData.WithSaltBuilder()
            .plainText(dto.getPassword())
            .salt(salt)
            .build();
        String encryptedPassword = encryptor.encrypt(cryptoData);
        User user = dto.toEntity(id, salt, encryptedPassword);

        userRepository.updateUserInformation(user);
    }
}