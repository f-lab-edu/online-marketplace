package com.coupang.marketplace.review.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Review {

    private long id;
    private long userId;
    private long productId;
    private String content;
    private int helpNum;
    private int noHelpNum;
    private int score;
    private LocalDateTime createdAt;

    @Builder
    public Review(long id, long userId, long productId, String content, int helpNum, int noHelpNum, int score, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.content = content;
        this.helpNum = helpNum;
        this.noHelpNum = noHelpNum;
        this.score = score;
        this.createdAt = createdAt;
    }
}
