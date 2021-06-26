package com.coupang.marketplace.util;

public interface Encryptor {

    public String run (String plainPassword, String salt);

}
