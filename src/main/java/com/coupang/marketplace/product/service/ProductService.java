package com.coupang.marketplace.product.service;

import com.coupang.marketplace.product.constant.DeliveryTypeEnum;
import com.coupang.marketplace.product.controller.dto.GetProductsResponse;
import com.coupang.marketplace.product.controller.dto.GetProductsRequest;
import com.coupang.marketplace.product.domain.Product;
import com.coupang.marketplace.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<GetProductsResponse> getProducts(GetProductsRequest dto){
        DeliveryTypeEnum deliveryType = dto.getDeliveryType();
        boolean isRocket = dto.getIsRocket();
        int listSize = dto.getListSize();
        int pageStart = (dto.getPage() - 1) * listSize;

        List<Product> products = new ArrayList<>();

        switch (deliveryType) {
            case ROCKET:
                products = productRepository.getProductsByIsRocket(TRUE, pageStart, listSize);
                break;
            case ROCKET_FRESH:
                if (isRocket) {
                    products = productRepository.getProductsByIsRocketAndIsRocketFresh(TRUE, TRUE, pageStart, listSize);
                } else {
                    products = productRepository.getProductsByIsRocketFresh(TRUE, pageStart, listSize);
                }
                break;
            case ROCKET_GLOBAL:
                if (isRocket) {
                    products = productRepository.getProductsByIsRocketAndIsRocketGlobal(TRUE, TRUE, pageStart, listSize);
                } else {
                    products = productRepository.getProductsByIsRocketGlobal(TRUE, pageStart, listSize);
                }
                break;
        }

        return GetProductsResponse.makeList(products);
    }

}
