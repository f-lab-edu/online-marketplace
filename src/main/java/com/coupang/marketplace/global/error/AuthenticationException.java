package com.coupang.marketplace.global.error;

public class AuthenticationException extends RuntimeException{
	public AuthenticationException(String e){
		super(e);
	}
}