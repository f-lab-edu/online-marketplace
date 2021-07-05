package com.coupang.marketplace.service;

import com.coupang.marketplace.controller.SignInRequestDto;
import com.coupang.marketplace.domain.User;
import com.coupang.marketplace.repository.UserRepository;
import com.coupang.marketplace.constant.SessionKey;
import com.coupang.marketplace.util.Sha256Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SessionLoginService implements LoginService{

    private final UserRepository userRepository;
    private final HttpSession httpSession;
    private final Sha256Encryptor sha256Encryptor;

    @Override
    public void login(SignInRequestDto dto){
        if (!userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        String userSalt = user.get().getSalt();
        String password = dto.getPassword();
        String encryptedPassword = sha256Encryptor.encrypt(password, userSalt);

        if(!encryptedPassword.equals(user.get().getPassword())){
            throw new IllegalArgumentException("패스워드가 틀렸습니다.");
        }
        httpSession.setAttribute(SessionKey.LOGIN_USER_ID, user.get().getId());
    }
}
