package com.coupang.marketplace.review.repository;

import com.coupang.marketplace.review.domain.Review;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReviewRepository {

    long insertReview(Review review);

}
