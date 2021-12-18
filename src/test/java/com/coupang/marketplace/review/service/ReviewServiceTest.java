package com.coupang.marketplace.review.service;

import com.coupang.marketplace.global.fixture.ImageFixture.*;
import com.coupang.marketplace.global.fixture.ReviewFixture.*;
import com.coupang.marketplace.global.fixture.UserFixture.*;
import com.coupang.marketplace.global.util.strorage.Storage;
import com.coupang.marketplace.review.controller.dto.CreateReviewRequest;
import com.coupang.marketplace.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private Storage storage;

    @DisplayName("리뷰 등록에 성공한다.")
    @Test
    void createReview() {
        // given
        final long userId = User1.ID;
        final CreateReviewRequest dto = CreateReviewRequest.builder()
                .productId(Review1.PRODUCT_ID)
                .score(Review1.SCORE)
                .content(Review1.CONTENT)
                .img(Image1.MULTIPART_FILE)
                .build();

        // when
        reviewService.createReview(userId, dto);

        // then
        then(reviewRepository).should(times(1)).insertReview(any());
        then(storage).should(times(1)).saveMultipartFile(any(), any());

    }
}