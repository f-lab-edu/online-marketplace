package com.coupang.marketplace.service;

import com.coupang.marketplace.controller.SignInRequestDto;
import com.coupang.marketplace.controller.SignUpRequestDto;
import com.coupang.marketplace.domain.User;
import com.coupang.marketplace.repository.UserRepository;
import com.coupang.marketplace.util.SaltGenerator;
import com.coupang.marketplace.util.SessionKey;
import com.coupang.marketplace.util.Sha256Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SaltGenerator saltGenerator;
    private final Sha256Encryptor sha256Encryptor;
    private final HttpSession httpSession;

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

    public void login(SignInRequestDto dto){
        if (!checkIsUserExist(dto.getEmail())) {
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
        else {
            Optional<User> user = userRepository.findByEmail(dto.getEmail());
            String userSalt = user.get().getSalt();

            String password = dto.getPassword();
            String encryptedPassword = sha256Encryptor.run(password, userSalt);

            if(encryptedPassword.equals(user.get().getPassword())){
                httpSession.setAttribute(SessionKey.LOGIN_USER_ID, user.get().getId());
            }
            else{
                throw new IllegalArgumentException("패스워드가 틀렸습니다.");
            }
        }
    }
}
