package com.coupang.marketplace.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class CryptoUtil {
    public static String generateSalt() {
        return BCrypt.gensalt(12);
    }

    public static String encryptPassword(String plainPassword, String salt) {
        return BCrypt.hashpw(plainPassword, salt);
    }
}
