package com.coupang.marketplace.product.controller;

import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.global.constant.CacheKey;
import com.coupang.marketplace.product.controller.dto.GetProductsRequest;
import com.coupang.marketplace.product.controller.dto.SimpleProduct;
import com.coupang.marketplace.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;


@RequestMapping("/products")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @Cacheable(key="#dto.start", value= CacheKey.PRODUCTS)
    @GetMapping
    public SuccessResponse getProducts(@Valid @ModelAttribute GetProductsRequest dto) {
        List<SimpleProduct> products = productService.getProducts(dto);
        return SuccessResponse.builder()
                .status(StatusEnum.OK)
                .message("상품 목록 가져오기 성공")
                .data(products)
                .build();
    }

    @GetMapping("/search")
    public SuccessResponse searchProductsByKeyword (@NotBlank @RequestParam String keyword) {
        List<SimpleProduct> products = productService.searchProductsByKeyword(keyword);
        return SuccessResponse.builder()
                .status(StatusEnum.OK)
                .message("상품 검색 성공")
                .data(products)
                .build();
    }
}
