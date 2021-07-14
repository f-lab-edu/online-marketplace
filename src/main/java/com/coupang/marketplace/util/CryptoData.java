package com.coupang.marketplace.util;

import lombok.Builder;
import lombok.Getter;


@Getter
public class CryptoData {

    private String plainText;
    private String salt;

    @Builder
    public CryptoData(String plainText) {
        this.plainText = plainText;
    }

    @Builder(builderClassName= "WithSaltBuilder", builderMethodName = "WithSaltBuilder")
    public CryptoData(String plainText, String salt) {
        this.plainText = plainText;
        this.salt = salt;
    }
}
