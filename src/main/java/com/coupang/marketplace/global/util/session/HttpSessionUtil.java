package com.coupang.marketplace.global.util.session;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.coupang.marketplace.global.constant.SessionKey;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class HttpSessionUtil {

	private final HttpSession httpSession;

	public Object getAttribute(){
		return httpSession.getAttribute(SessionKey.LOGIN_USER_ID);
	}

	public void setAttribute(Object value){
		httpSession.setAttribute(SessionKey.LOGIN_USER_ID, value);
	}

	public void removeAttribute(){
		httpSession.removeAttribute(SessionKey.LOGIN_USER_ID);
	}
}