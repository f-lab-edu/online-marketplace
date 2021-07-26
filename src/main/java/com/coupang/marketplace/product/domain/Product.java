package com.coupang.marketplace.product.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;

@Getter
public class Product {

    private BigInteger id;
    private BigInteger categoryId;
    private String name;
    private BigInteger price;
    private String mainImg;
    private String detailImg;
    private BigInteger stock;
    private Float score;
    private BigInteger deliveryFee;
    private Boolean isRocket;
    private Boolean isRocketFresh;
    private Boolean isRocketGlobal;

    @Builder
    public Product(BigInteger id, BigInteger categoryId, String name, BigInteger price, String mainImg, String detailImg, BigInteger stock, Float score, BigInteger deliveryFee, Boolean isRocket, Boolean isRocketFresh, Boolean isRocketGlobal) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.mainImg = mainImg;
        this.detailImg = detailImg;
        this.stock = stock;
        this.score = score;
        this.deliveryFee = deliveryFee;
        this.isRocket = isRocket;
        this.isRocketFresh = isRocketFresh;
        this.isRocketGlobal = isRocketGlobal;
    }

}
