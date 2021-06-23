package com.coupang.marketplace.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class CryptoUtil {
    public static String generateSalt(){
        Random random = new Random();

        byte[] salt = new byte[8];
        random.nextBytes(salt);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < salt.length; i++) {
            sb.append(String.format("%02x",salt[i]));
        }

        return sb.toString();

    }

    public static String encryptPassword(String plainPassword, String salt) {
        String result = "";

        byte[] byteSalt = salt.getBytes();
        byte[] a = plainPassword.getBytes();
        byte[] bytes = new byte[a.length + byteSalt.length];

        System.arraycopy(a, 0, bytes, 0, a.length);
        System.arraycopy(byteSalt, 0, bytes, a.length, byteSalt.length);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);

            byte[] byteData = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }

            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }
}