package com.coupang.marketplace.user.service;

import com.coupang.marketplace.user.controller.SignInRequestDto;
import com.coupang.marketplace.user.domain.User;
import com.coupang.marketplace.user.repository.UserRepository;
import com.coupang.marketplace.global.constant.SessionKey;
import com.coupang.marketplace.global.util.crypto.CryptoData;
import com.coupang.marketplace.global.util.crypto.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SessionLoginService implements LoginService{

    private final UserRepository userRepository;
    private final HttpSession httpSession;
    private final Encryptor encryptor;

    @Override
    public void login(SignInRequestDto dto){
        if (!userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        CryptoData cryptoData = CryptoData.WithSaltBuilder()
            .plainText(dto.getPassword())
            .salt(user.get().getSalt())
            .build();
        String encryptedPassword = encryptor.encrypt(cryptoData);

        if(!encryptedPassword.equals(user.get().getPassword())){
            throw new IllegalArgumentException("패스워드가 틀렸습니다.");
        }
        httpSession.setAttribute(SessionKey.LOGIN_USER_ID, user.get().getId());
    }

    @Override
    public void logout(){
        httpSession.removeAttribute(SessionKey.LOGIN_USER_ID);
    }
}
