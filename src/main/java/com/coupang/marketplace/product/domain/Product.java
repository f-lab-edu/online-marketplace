package com.coupang.marketplace.product.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;

@Getter
public class Product {

    private long id;
    private BigInteger categoryId;
    private String name;
    private long price;
    private String mainImg;
    private String detailImg;
    private BigInteger stock;
    private Float score;
    private BigInteger deliveryFee;
    private Boolean rocket;
    private Boolean rocketFresh;
    private Boolean rocketGlobal;

    @Builder
    public Product(long id, BigInteger categoryId, String name, long price, String mainImg, String detailImg, BigInteger stock, Float score, BigInteger deliveryFee, Boolean rocket, Boolean rocketFresh, Boolean rocketGlobal) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.mainImg = mainImg;
        this.detailImg = detailImg;
        this.stock = stock;
        this.score = score;
        this.deliveryFee = deliveryFee;
        this.rocket = rocket;
        this.rocketFresh = rocketFresh;
        this.rocketGlobal = rocketGlobal;
    }
}
