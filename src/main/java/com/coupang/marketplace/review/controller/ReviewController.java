package com.coupang.marketplace.review.controller;

import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.review.controller.dto.CreateReviewRequest;
import com.coupang.marketplace.review.controller.dto.EvaluateReviewRequest;
import com.coupang.marketplace.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import com.coupang.marketplace.auth.AuthRequired;
import com.coupang.marketplace.user.service.LoginService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/reviews")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;
    @Qualifier("userSessionLoginService")
    private final LoginService loginService;


    @AuthRequired
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SuccessResponse createReview(@Valid CreateReviewRequest dto){
        Long userId = loginService.getLoginUserId();
        reviewService.createReview(userId, dto);
        return SuccessResponse.builder()
                .message("리뷰 등록 성공")
                .build();
    }

    @AuthRequired
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/evaluate")
    public SuccessResponse evaluateReview(@Valid @RequestBody EvaluateReviewRequest dto){
        Long userId = loginService.getLoginUserId();
        reviewService.evaluateReview(userId, dto);
        return SuccessResponse.builder()
                .status(StatusEnum.CREATED)
                .message("리뷰 평가 완료")
                .build();
    }
}