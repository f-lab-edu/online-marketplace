package com.coupang.marketplace.review.controller;

import com.coupang.marketplace.global.fixture.ImageFixture.*;
import com.coupang.marketplace.global.fixture.ReviewEvaluationFixture.*;
import com.coupang.marketplace.global.fixture.ReviewFixture.*;
import com.coupang.marketplace.global.template.ControllerTestTemplate;
import com.coupang.marketplace.review.controller.dto.EvaluateReviewRequest;
import com.coupang.marketplace.review.domain.Evaluation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import com.coupang.marketplace.global.constant.SessionKey;
import com.coupang.marketplace.global.fixture.UserFixture;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReviewControllerTest extends ControllerTestTemplate {

    @DisplayName("리뷰 정보를 보내면 리뷰가 등록된다.")
    @Test
    public void createReview() throws Exception{
        // given
        final String productId = String.valueOf(Review1.PRODUCT_ID);
        final String score = String.valueOf(Review1.SCORE);
        final String content = Review1.CONTENT;
        final MultipartFile file = Image1.MULTIPART_FILE;

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionKey.LOGIN_USER_ID, UserFixture.User1.ID);

        // when
        final ResultActions actions = mvc.perform(multipart("/reviews")
                .file("img", file.getBytes())
                .param("productId", String.valueOf(Review1.PRODUCT_ID))
                .param("score", String.valueOf(Review1.SCORE))
                .param("content", Review1.CONTENT)
                .session(session))
                .andDo(print());

        // then
        actions
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("리뷰 평가 정보를 보내면 리뷰 평가가 등록된다.")
    @Test
    public void evaluateReview() throws Exception{
        // given
        final EvaluateReviewRequest dto = EvaluateReviewRequest.builder()
                .reviewId(ReviewEvaluation1.ID)
                .evaluation(Evaluation.HELP)
                .build();

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionKey.LOGIN_USER_ID, UserFixture.User1.ID);

        // when
        final ResultActions actions = mvc.perform(post("/reviews/evaluate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print());

        // then
        actions
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
