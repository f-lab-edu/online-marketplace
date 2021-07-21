package com.coupang.marketplace.product.controller;

import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.product.controller.dto.GetProductsRequest;
import com.coupang.marketplace.product.controller.dto.GetProductsResponse;
import com.coupang.marketplace.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<SuccessResponse> getProducts(@Valid @ModelAttribute GetProductsRequest dto) {
        List<GetProductsResponse> products = productService.getProducts(dto);
        SuccessResponse res = SuccessResponse.builder()
                .status(StatusEnum.OK)
                .message("상품 목록 가져오기 성공")
                .data(products)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
