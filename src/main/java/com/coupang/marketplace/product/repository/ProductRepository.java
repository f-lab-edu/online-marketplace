package com.coupang.marketplace.product.repository;

import com.coupang.marketplace.product.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ProductRepository {

    List<Product> getProductsByIsRocket(boolean isRocket, int startId, int listSize);

    List<Product> getProductsByIsRocketFresh(boolean isRocketFresh, int startId, int listSize);

    List<Product> getProductsByIsRocketGlobal(boolean isRocketGlobal, int startId, int listSize);

    List<Product> getProductsByIsRocketAndIsRocketFresh(boolean isRocket, boolean isRocketFresh, int startId, int listSize);

    List<Product> getProductsByIsRocketAndIsRocketGlobal(boolean isRocket, boolean isRocketGlobal, int startId, int listSize);

    List<Product> getProductsByKeyword(String keyword);

    Optional<Product> findByProductId(BigInteger id);
}
