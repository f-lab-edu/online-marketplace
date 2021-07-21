package com.coupang.marketplace.product.controller.dto;

import com.coupang.marketplace.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class GetProductsResponse {

    @NotNull
    private BigInteger id;

    @NotNull
    private String name;

    @NotNull
    private BigInteger price;

    @NotNull
    private String mainImg;

    @NotNull
    private float score;

    @Builder
    public GetProductsResponse(BigInteger id, String name, BigInteger price, String mainImg, float score) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mainImg = mainImg;
        this.score = score;
    }

    public static GetProductsResponse makeEntityToResponse(Product product) {
        return GetProductsResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .mainImg(product.getMainImg())
                .score(product.getScore())
                .build();
    }

    public static List<GetProductsResponse> makeList(List<Product> products) {
        return products.stream()
                .map(GetProductsResponse::makeEntityToResponse)
                .collect(Collectors.toList());
    }

}