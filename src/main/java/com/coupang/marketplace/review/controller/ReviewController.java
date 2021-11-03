package com.coupang.marketplace.review.controller;

import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.review.controller.dto.CreateReviewRequest;
import com.coupang.marketplace.review.controller.dto.EvaluateReviewRequest;
import com.coupang.marketplace.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/reviews")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SuccessResponse createReview(@Valid CreateReviewRequest dto){
        long userId = 1; // 하드코딩
        reviewService.createReview(userId, dto);
        return SuccessResponse.builder()
                .message("리뷰 등록 성공")
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/evaluate")
    public SuccessResponse evaluateReview(@Valid @RequestBody EvaluateReviewRequest dto){
        long userId = 1; // 하드코딩
        reviewService.evaluateReview(userId, dto);
        return SuccessResponse.builder()
                .status(StatusEnum.CREATED)
                .message("리뷰 평가 완료")
                .build();
    }
}
