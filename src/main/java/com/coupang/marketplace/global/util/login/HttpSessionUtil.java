package com.coupang.marketplace.global.util.login;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.coupang.marketplace.global.constant.SessionKey;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class HttpSessionUtil implements LoginUtil{

	private final HttpSession httpSession;

	@Override
	public Object getAttribute(){
		return httpSession.getAttribute(SessionKey.LOGIN_USER_ID);
	}

	@Override
	public void setAttribute(Object value){
		httpSession.setAttribute(SessionKey.LOGIN_USER_ID, value);
	}

	@Override
	public void removeAttribute(){
		httpSession.removeAttribute(SessionKey.LOGIN_USER_ID);
	}
}