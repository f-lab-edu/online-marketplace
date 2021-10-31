package com.coupang.marketplace.review.controller.dto;

import com.coupang.marketplace.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CreateReviewRequest {

    @NotNull
    private long productId;

    @Min(1)
    @Max(10)
    @NotNull
    private int score;

    @NotBlank
    private String content;

    @NotNull
    private MultipartFile img;

    @Builder
    public CreateReviewRequest(long productId, int score, String content, MultipartFile img) {
        this.productId = productId;
        this.score = score;
        this.content = content;
        this.img = img;
    }

    public Review toEntity(long userId){
        return Review.builder()
                .userId(userId)
                .productId(productId)
                .content(content)
                .score(score)
                .build();
    }
}
