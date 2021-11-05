package com.coupang.marketplace.product.controller;

import com.coupang.marketplace.auth.AuthRequired;
import com.coupang.marketplace.cart.service.CartService;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.global.constant.CacheKey;
import com.coupang.marketplace.product.controller.dto.GetProductsRequest;
import com.coupang.marketplace.product.controller.dto.SaveToCartRequest;
import com.coupang.marketplace.product.controller.dto.SimpleProduct;
import com.coupang.marketplace.product.service.ProductService;
import com.coupang.marketplace.user.service.LoginService;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.util.List;


@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;
    private final CartService cartService;
    private final LoginService loginService;

    public ProductController(ProductService productService, CartService cartService,  @Qualifier("userSessionLoginService")LoginService loginService){
        this.productService = productService;
        this.cartService = cartService;
        this.loginService = loginService;
    }

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

    @AuthRequired
    @PostMapping("/cart")
    public SuccessResponse saveProductsToCart(@Valid @RequestBody final SaveToCartRequest dto){
        long userId = loginService.getLoginUserId();
        cartService.saveProduct(userId, dto);
        return SuccessResponse.builder()
            .status(StatusEnum.OK)
            .message("장바구니에 상품 담기 성공")
            .build();
    }
}