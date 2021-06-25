package com.coupang.marketplace.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SaltGenerator {

    public String run(){
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
