package com.coupang.marketplace.review.service;


import com.coupang.marketplace.global.util.strorage.Storage;
import com.coupang.marketplace.review.controller.dto.CreateReviewRequest;
import com.coupang.marketplace.review.controller.dto.EvaluateReviewRequest;
import com.coupang.marketplace.review.domain.Evaluation;
import com.coupang.marketplace.review.domain.Review;
import com.coupang.marketplace.review.domain.ReviewEvaluation;
import com.coupang.marketplace.review.repository.ReviewEvaluationRepository;
import com.coupang.marketplace.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewEvaluationRepository reviewEvaluationRepository;
    @Qualifier("ncpStorage")
    private final Storage storage;

    @Transactional
    public void createReview(long userId, CreateReviewRequest dto){
        Review review = dto.toEntity(userId);
        reviewRepository.insertReview(review);

        long reviewId = review.getId();
        MultipartFile img = dto.getImg();
        storage.saveMultipartFile(img, String.valueOf(reviewId));
    }

    @Transactional
    public void evaluateReview(long userId, EvaluateReviewRequest dto){
        Optional<ReviewEvaluation> pastReviewEvaluation = reviewEvaluationRepository.getReviewEvaluationByUserIdAndReviewId(userId, dto.getReviewId());
        if (pastReviewEvaluation.isPresent()) {
            reviewEvaluationRepository.deleteReviewEvaluation(pastReviewEvaluation.get().getId());
        }

        ReviewEvaluation reviewEvaluation = dto.toEntity(userId);
        reviewEvaluationRepository.insertReviewEvaluation(reviewEvaluation);
    }

}
