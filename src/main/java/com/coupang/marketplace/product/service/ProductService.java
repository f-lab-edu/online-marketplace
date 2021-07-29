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


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<GetProductsResponse> getProducts(GetProductsRequest dto){
        DeliveryTypeEnum deliveryType = dto.getDeliveryType();
        boolean isRocket = dto.isRocket();
        int listSize = dto.getListSize();
        int pageStart = (dto.getPage() - 1) * listSize;

        List<Product> products = getProductsByDeliveryType(deliveryType, isRocket, listSize, pageStart);

        return GetProductsResponse.toList(products);
    }

    private List<Product> getProductsByDeliveryType (DeliveryTypeEnum deliveryType, boolean isRocket, int listSize, int pageStart) {
        List<Product> products = new ArrayList<>();

        switch (deliveryType) {
            case ROCKET:
                products = productRepository.getProductsByIsRocket(true, pageStart, listSize);
                break;
            case ROCKET_FRESH:
                if (isRocket) {
                    products = productRepository.getProductsByIsRocketAndIsRocketFresh(true, true, pageStart, listSize);
                } else {
                    products = productRepository.getProductsByIsRocketFresh(true, pageStart, listSize);
                }
                break;
            case ROCKET_GLOBAL:
                if (isRocket) {
                    products = productRepository.getProductsByIsRocketAndIsRocketGlobal(true, true, pageStart, listSize);
                } else {
                    products = productRepository.getProductsByIsRocketGlobal(true, pageStart, listSize);
                }
                break;
        }

        return products;
    }

}
