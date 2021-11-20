package com.coupang.marketplace.product.controller.dto;

import com.coupang.marketplace.product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class SimpleProduct {
    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    private long price;

    @NotNull
    private String mainImg;

    @NotNull
    private float score;

    @Builder
    public SimpleProduct(long id, String name, long price, String mainImg, float score) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mainImg = mainImg;
        this.score = score;
    }

    public static SimpleProduct toResponse(Product product) {
        return SimpleProduct.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .mainImg(product.getMainImg())
                .score(product.getScore())
                .build();
    }

    public static List<SimpleProduct> toList(List<Product> products) {
        return products.stream()
                .map(SimpleProduct::toResponse)
                .collect(Collectors.toList());
    }
}
