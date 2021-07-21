package com.coupang.marketplace.product.repository;

import com.coupang.marketplace.product.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ProductRepository {

    List<Product> getProductsByIsRocket(boolean isRocket, int pageStart, int listSize);

    List<Product> getProductsByIsRocketFresh(boolean isRocketFresh, int pageStart, int listSize);

    List<Product> getProductsByIsRocketGlobal(boolean isRocketGlobal, int pageStart, int listSize);

    List<Product> getProductsByIsRocketAndIsRocketFresh(boolean isRocket, boolean isRocketFresh, int pageStart, int listSize);

    List<Product> getProductsByIsRocketAndIsRocketGlobal(boolean isRocket, boolean isRocketGlobal, int pageStart, int listSize);
}
