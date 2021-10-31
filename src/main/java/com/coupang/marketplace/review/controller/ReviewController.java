package com.coupang.marketplace.review.controller;

import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.review.controller.dto.CreateReviewRequest;
import com.coupang.marketplace.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/reviews")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public SuccessResponse createReview(@Valid @ModelAttribute CreateReviewRequest dto){
        reviewService.createReview(dto);
        return SuccessResponse.builder()
                .message("리뷰 등록 성공")
                .build();
    }
}
