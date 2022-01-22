package com.coupang.marketplace.global.fixture;

import com.coupang.marketplace.review.domain.ReviewEvaluation;

public class ReviewEvaluationFixture {

    public static class ReviewEvaluation1 {
        public static final long ID = 1L;
        public static final long USER_ID = 1L;
        public static final long REVIEW_ID = 1L;
        public static final boolean IS_HELP = true;

        public static final ReviewEvaluation REVIEW_EVALUATION = ReviewEvaluation.builder()
                .id(ID)
                .userId(USER_ID)
                .reviewId(REVIEW_ID)
                .isHelp(IS_HELP)
                .build();
    }
}
