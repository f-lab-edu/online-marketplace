package com.coupang.marketplace.global.util;

import java.util.Random;

public class SaltGenerator {

    public static String generateSalt(){
        Random random = new Random();

        byte[] salt = new byte[8];
        random.nextBytes(salt);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < salt.length; i++) {
            sb.append(String.format("%02x",salt[i]));
        }

        return sb.toString();
    }
}



