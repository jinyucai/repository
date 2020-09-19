package com.cai.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Value("${spring.security.loginType}")
    private String loginType;

    /**
     * 登录失败操作
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        exception.printStackTrace();
        String errorMessage = "用户名或密码错误";
        if(exception instanceof SessionAuthenticationException) {
            errorMessage = exception.getMessage();
        }
        if(loginType.equalsIgnoreCase("JSON")) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(errorMessage);
        }else {
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
