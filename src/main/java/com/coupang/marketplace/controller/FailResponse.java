package com.coupang.marketplace.controller;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FailResponse {

    private StatusEnum status;
    private String errorMessage;

    @Builder
    public FailResponse(StatusEnum status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
