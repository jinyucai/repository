package com.cai.auth;

import com.cai.entity.CaptchaCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

//自定义拦截器
@Component
public class MyKaptchaFilter extends OncePerRequestFilter {

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String uri = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        //只有登录请求才做验证码验证
        if(uri.equals("/login") && method.equalsIgnoreCase("post")) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
            }catch (AuthenticationException e) {
                //抛给自定义登录验证失败处理器
                myAuthenticationFailureHandler.onAuthenticationFailure(httpServletRequest,
                        httpServletResponse, e);
                return;
            }
        }
        //继续执行下面的过滤器
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    public void validate(ServletWebRequest request) throws ServletRequestBindingException {
        HttpSession session = request.getRequest().getSession();
        //获取前台验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"captchaCode");
        if(StringUtils.isEmpty(codeInRequest)){
            throw new SessionAuthenticationException("验证码不能为空");
        }
        // 3. 获取session池中的验证码谜底
        CaptchaCode codeInSession = (CaptchaCode) session.getAttribute("kaptcha_key");
        if(Objects.isNull(codeInSession)) {
            throw new SessionAuthenticationException("验证码不存在");
        }
        // 4. 校验服务器session池中的验证码是否过期
        if(codeInSession.isExpired()) {
            session.removeAttribute("kaptcha_key");
            throw new SessionAuthenticationException("验证码已经过期");
        }
        // 5. 请求验证码校验
        if(!codeInRequest.equalsIgnoreCase(codeInSession.getCode())) {
            throw new SessionAuthenticationException("验证码不匹配");
        }
        session.removeAttribute("kaptcha_key");
    }
}
