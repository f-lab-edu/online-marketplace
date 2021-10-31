package com.coupang.marketplace.review.service;


import com.coupang.marketplace.global.util.strorage.Storage;
import com.coupang.marketplace.review.controller.dto.CreateReviewRequest;
import com.coupang.marketplace.review.domain.Review;
import com.coupang.marketplace.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    @Qualifier("ncpStorage")
    private final Storage storage;

    @Transactional
    public void createReview(CreateReviewRequest dto){
        long userId = 1; // 하드코딩

        Review review = dto.toEntity(userId);
        reviewRepository.insertReview(review);

        long reviewId = review.getId();
        MultipartFile img = dto.getImg();
        storage.saveMultipartFile(img, String.valueOf(reviewId));
    }

}
