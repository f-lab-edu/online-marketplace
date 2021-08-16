package com.coupang.marketplace.auth;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.coupang.marketplace.global.error.AuthenticationException;

@Aspect
@Component
public class LoginAuthAspect {

	@Before("@annotation(com.coupang.marketplace.auth.LoginAuth) && @annotation(loginAuth)")
	public void loginAuth(LoginAuth loginAuth){
		HttpSession httpSession;
		try {
			httpSession = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
		} catch(IllegalStateException e){
			throw new AuthenticationException("로그인 후 이용가능합니다.");
		}
		verifySession(httpSession, loginAuth.type());
	}

	private void verifySession(HttpSession httpSession, LoginType type){
		Long userId = (Long)httpSession.getAttribute(type.getSessionKey());

		if(userId == null)
			throw new AuthenticationException("로그인 후 이용가능합니다.");
	}
}