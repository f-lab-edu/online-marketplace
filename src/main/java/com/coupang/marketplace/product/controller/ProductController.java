package com.coupang.marketplace.product.controller;

import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.product.controller.dto.GetProductsRequest;
import com.coupang.marketplace.product.controller.dto.GetProductsResponse;
import com.coupang.marketplace.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;
    
    @GetMapping("/products")
    public SuccessResponse getProducts(@Valid @ModelAttribute GetProductsRequest dto) {
        List<GetProductsResponse> products = productService.getProducts(dto);
        SuccessResponse res = SuccessResponse.builder()
                .status(StatusEnum.OK)
                .message("상품 목록 가져오기 성공")
                .data(products)
                .build();
        return res;
    }

    @GetMapping("/products/search")
    public SuccessResponse searchProductsByKeyword (@NotBlank @RequestParam String keyword) {
        List<GetProductsResponse> products = productService.searchProductsByKeyword(keyword);
        SuccessResponse res = SuccessResponse.builder()
                .status(StatusEnum.OK)
                .message("상품 검색 성공")
                .data(products)
                .build();
        return res;
    }
}
