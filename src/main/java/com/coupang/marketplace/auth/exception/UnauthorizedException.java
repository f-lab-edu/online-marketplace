package com.coupang.marketplace.auth.exception;

public class UnauthorizedException extends RuntimeException {

    private static final String message = "인증되지 않은 사용자입니다.";

    public UnauthorizedException(){
        super(message);
    }

    public UnauthorizedException(Exception e){
        super(message, e);
    }
}
