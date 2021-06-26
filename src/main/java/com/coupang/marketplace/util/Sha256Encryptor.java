package com.coupang.marketplace.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Component
public class Sha256Encryptor implements Encryptor {

    private static final String algorithm = "SHA-256";

    @Override
    public String run(String plainPassword, String salt) {
        String result = "";

        byte[] byteSalt = salt.getBytes();
        byte[] a = plainPassword.getBytes();
        byte[] bytes = new byte[a.length + byteSalt.length];

        System.arraycopy(a, 0, bytes, 0, a.length);
        System.arraycopy(byteSalt, 0, bytes, a.length, byteSalt.length);

        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(bytes);

            byte[] byteData = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }

            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("암호화에 실패하였습니다.");
        }

        return result;
    }
}