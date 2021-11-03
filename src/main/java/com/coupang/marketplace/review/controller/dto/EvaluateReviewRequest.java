package com.coupang.marketplace.review.controller.dto;

import com.coupang.marketplace.review.domain.Evaluation;
import com.coupang.marketplace.review.domain.ReviewEvaluation;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class EvaluateReviewRequest {

    @NotNull
    private long reviewId;

    @NotNull
    private Evaluation evaluation;

    @Builder
    public EvaluateReviewRequest(long reviewId, Evaluation evaluation) {
        this.reviewId = reviewId;
        this.evaluation = evaluation;
    }

    public ReviewEvaluation toEntity(long userId){
        return ReviewEvaluation.builder()
                .userId(userId)
                .reviewId(reviewId)
                .isHelp(evaluation == Evaluation.HELP)
                .build();
    }
}

