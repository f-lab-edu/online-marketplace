package com.coupang.marketplace.review.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewEvaluation {

    private long id;
    private long userId;
    private long reviewId;
    private boolean isHelp;

    @Builder
    public ReviewEvaluation(long id, long userId, long reviewId, boolean isHelp) {
        this.id = id;
        this.userId = userId;
        this.reviewId = reviewId;
        this.isHelp = isHelp;
    }
}
