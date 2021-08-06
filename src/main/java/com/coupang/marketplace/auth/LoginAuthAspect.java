package com.coupang.marketplace.auth;

import static com.coupang.marketplace.auth.LoginType.*;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.coupang.marketplace.global.constant.SessionKey;
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
			throw new AuthenticationException();
		}
		if(loginAuth.type() == USER)
			verifyUserSession(httpSession);
	}

	private void verifyUserSession(HttpSession httpSession){
		Long userId = (Long)httpSession.getAttribute(SessionKey.LOGIN_USER_ID);

		if(userId == null)
			throw new AuthenticationException();
	}
}