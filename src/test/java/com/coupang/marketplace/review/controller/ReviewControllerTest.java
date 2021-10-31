package com.coupang.marketplace.review.controller;

import com.coupang.marketplace.global.fixture.ImageFixture.*;
import com.coupang.marketplace.global.fixture.ReviewFixture.*;
import com.coupang.marketplace.global.template.ControllerTestTemplate;
import com.coupang.marketplace.review.controller.dto.CreateReviewRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReviewControllerTest extends ControllerTestTemplate {

    @DisplayName("리뷰 정보를 보내면 리뷰가 등록된다.")
    @Test
    public void createReview() throws Exception{
        // given
        final CreateReviewRequest dto = CreateReviewRequest.builder()
                .productId(Review1.PRODUCT_ID)
                .score(Review1.SCORE)
                .content(Review1.CONTENT)
                .img(Image1.MULTIPART_FILE)
                .build();

        // when
        final ResultActions actions = mvc.perform(post("/reviews")
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print());

        // then
        actions
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
