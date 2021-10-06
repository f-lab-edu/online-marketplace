package com.coupang.marketplace.global.error;

public class NotAuthorizedException extends RuntimeException{

	public NotAuthorizedException(String errorMessage){
		super(errorMessage);
	}

	public NotAuthorizedException(String errorMessage, NullPointerException e){
		super(errorMessage, e.getCause());
	}
}