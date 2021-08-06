package com.coupang.marketplace.global.error;

public class AuthenticationException extends RuntimeException{

	private static final String errorMessage = "로그인 후 이용가능합니다.";
	public AuthenticationException(){
		super(errorMessage);
	}
}