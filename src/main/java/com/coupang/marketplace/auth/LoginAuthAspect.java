package com.coupang.marketplace.auth;

import static com.coupang.marketplace.auth.LoginType.*;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
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
			throw new AuthenticationException("로그인 후 이용가능합니다.");
		}
		if(loginAuth.type() == USER)
			verifyUserSession(httpSession);
	}

	private void verifyUserSession(HttpSession httpSession){
		Long userId = (Long)httpSession.getAttribute(SessionKey.LOGIN_USER_ID);

		if(userId == null)
			throw new AuthenticationException("로그인 후 이용가능합니다.");
	}

	@Around("execution(* *(.., @UserId (*), ..))")
	public Object loginAuth2(ProceedingJoinPoint joinPoint) throws Throwable{
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
		Long userId = (Long)session.getAttribute(SessionKey.LOGIN_USER_ID);

		Long paramId = null;
		Object[] signatureArgs = joinPoint.getArgs();
		for (Object signatureArg: signatureArgs) {
			paramId = (Long)signatureArg;
			break;
		}

		if(!paramId.equals(userId))
			throw new AuthenticationException("다른 사용자의 정보에 접근하였습니다.");
		return joinPoint.proceed(signatureArgs);
	}
}