package com.coupang.marketplace.global.fixture;

import com.coupang.marketplace.product.domain.Product;

import java.math.BigInteger;

public class ProductFixture {

    public static class Product1 {
        public static final BigInteger ID = new BigInteger("1");
        public static final BigInteger CATEGORY_ID  = new BigInteger("1");
        public static final String NAME =  "상품1";
        public static final BigInteger PRICE = new BigInteger("10000");
        public static final String MAIN_IMG = "/img/main/1.png";
        public static final String DETAIL_IMG = "/img/detail/1.png";
        public static final BigInteger STOCK = new BigInteger("1000");
        public static final float SCORE = 5.0f;
        public static final BigInteger DELIVERY_FEE = new BigInteger("1000");
        public static final boolean ROCKET = true;
        public static final boolean ROCKET_FRESH = false;
        public static final boolean ROCKET_GLOBAL = false;

        public static final Product PRODUCT = Product.builder()
                .id(ID)
                .categoryId(CATEGORY_ID)
                .name(NAME)
                .price(PRICE)
                .mainImg(MAIN_IMG)
                .detailImg(DETAIL_IMG)
                .stock(STOCK)
                .score(SCORE)
                .deliveryFee(DELIVERY_FEE)
                .rocket(ROCKET)
                .rocketFresh(ROCKET_FRESH)
                .rocketGlobal(ROCKET_GLOBAL)
                .build();
    }

    public static class Product2 {
        public static final BigInteger ID = new BigInteger("2");
        public static final BigInteger CATEGORY_ID  = new BigInteger("2");
        public static final String NAME =  "상품2";
        public static final BigInteger PRICE = new BigInteger("20000");
        public static final String MAIN_IMG = "/img/main/2.png";
        public static final String DETAIL_IMG = "/img/detail/2.png";
        public static final BigInteger STOCK = new BigInteger("2000");
        public static final float SCORE = 4.0f;
        public static final BigInteger DELIVERY_FEE = new BigInteger("2000");
        public static final boolean ROCKET = false;
        public static final boolean ROCKET_FRESH = true;
        public static final boolean ROCKET_GLOBAL = false;

        public static final Product PRODUCT = Product.builder()
                .id(ID)
                .categoryId(CATEGORY_ID)
                .name(NAME)
                .price(PRICE)
                .mainImg(MAIN_IMG)
                .detailImg(DETAIL_IMG)
                .stock(STOCK)
                .score(SCORE)
                .deliveryFee(DELIVERY_FEE)
                .rocket(ROCKET)
                .rocketFresh(ROCKET_FRESH)
                .rocketGlobal(ROCKET_GLOBAL)
                .build();
    }

    public static class Product3 {
        public static final BigInteger ID = new BigInteger("3");
        public static final BigInteger CATEGORY_ID  = new BigInteger("3");
        public static final String NAME =  "상품3";
        public static final BigInteger PRICE = new BigInteger("30000");
        public static final String MAIN_IMG = "/img/main/3.png";
        public static final String DETAIL_IMG = "/img/detail/3.png";
        public static final BigInteger STOCK = new BigInteger("3000");
        public static final float SCORE = 3.0f;
        public static final BigInteger DELIVERY_FEE = new BigInteger("3000");
        public static final boolean ROCKET = false;
        public static final boolean ROCKET_FRESH = false;
        public static final boolean ROCKET_GLOBAL = true;

        public static final Product PRODUCT = Product.builder()
                .id(ID)
                .categoryId(CATEGORY_ID)
                .name(NAME)
                .price(PRICE)
                .mainImg(MAIN_IMG)
                .detailImg(DETAIL_IMG)
                .stock(STOCK)
                .score(SCORE)
                .deliveryFee(DELIVERY_FEE)
                .rocket(ROCKET)
                .rocketFresh(ROCKET_FRESH)
                .rocketGlobal(ROCKET_GLOBAL)
                .build();
    }
}
