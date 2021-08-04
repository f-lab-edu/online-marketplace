package com.coupang.marketplace.auth;

import com.coupang.marketplace.auth.exception.UnauthorizedException;
import com.coupang.marketplace.global.constant.SessionKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnauthorizedException {
        try {
            if (needToAuth((HandlerMethod)handler)) {
                String userIdBySession = getUserIdBySession(request);
                String userIdByPath = getUserIdByPathVariable(request);
                if (!userIdBySession.equals(userIdByPath)) {
                    throw new UnauthorizedException();
                };
            }
            return true;
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }

    private boolean needToAuth(HandlerMethod handler){
        if (handler.getMethodAnnotation(AuthRequired.class) == null) {
            return false;
        }
        return true;
    }

    private String getUserIdBySession(HttpServletRequest request){
        HttpSession session = request.getSession();
        return session.getAttribute(SessionKey.LOGIN_USER_ID)
                .toString();
    }

    private String getUserIdByPathVariable(HttpServletRequest request){
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return pathVariables.get("id");
    }
}
