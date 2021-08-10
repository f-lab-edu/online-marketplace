package com.coupang.marketplace.auth.exception;

public class NoAuthorizationData extends RuntimeException {

    private static final String message = "사용자 인증 정보가 부족합니다.";

    public NoAuthorizationData(){
        super(message);
    }
}
