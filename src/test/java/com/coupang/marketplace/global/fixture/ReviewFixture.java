package com.coupang.marketplace.global.fixture;

import com.coupang.marketplace.review.domain.Review;

import java.time.LocalDateTime;

public class ReviewFixture {

    public static class Review1 {
        public static final long ID = 1L;
        public static final long USER_ID = 1L;
        public static final long PRODUCT_ID = 1L;
        public static final String CONTENT = "리뷰 내용 1";
        public static final int HELP_NUM = 0;
        public static final int NO_HELP_NUM = 0;
        public static final int SCORE = 10;
        public static final LocalDateTime CREATED_AT = LocalDateTime.of(2021, 10, 1, 10, 0, 0);

        public static final Review REVIEW = Review.builder()
                .id(ID)
                .userId(USER_ID)
                .productId(PRODUCT_ID)
                .content(CONTENT)
                .helpNum(HELP_NUM)
                .noHelpNum(NO_HELP_NUM)
                .score(SCORE)
                .build();
    }

}
