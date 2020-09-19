package com.cai.auth.sms;

import com.cai.auth.MyAuthenticationFailureHandler;
import com.cai.auth.MyUserDetails;
import com.cai.auth.MyUserDetailsServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
@Component
public class SmsCodeValidateFilter extends OncePerRequestFilter {

    @Autowired
    MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals("/smslogin",httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")){

            try{
                //验证谜底与用户输入是否匹配
                validate(new ServletWebRequest(httpServletRequest));
            }catch(AuthenticationException e){
                myAuthenticationFailureHandler.onAuthenticationFailure(
                        httpServletRequest,httpServletResponse,e
                );
                return;
            }

        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    //验证规则
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        HttpSession session = request.getRequest().getSession();
        SmsCode codeInSession = (SmsCode)session.getAttribute("mobile_key");
        String mobileInRequest = request.getParameter("mobile");
        String codeInRequest = request.getParameter("smsCode");


        if(StringUtils.isEmpty(mobileInRequest)){
            throw new SessionAuthenticationException("手机号码不能为空");
        }

        if(StringUtils.isEmpty(codeInRequest)) {
            throw new SessionAuthenticationException("短信验证码不能为空");
        }

        if(Objects.isNull(codeInSession)) {
            throw new SessionAuthenticationException("短信验证码不存在");
        }


        if(codeInSession.isExpired()) {
            session.removeAttribute("mobile_key");
            throw new SessionAuthenticationException("短信验证码已经过期");
        }

        if(!codeInSession.getCode().equals(codeInRequest)) {
            throw new SessionAuthenticationException("短信验证码不正确");
        }

        if(!codeInSession.getMobile().equals(mobileInRequest)) {
            throw new SessionAuthenticationException("短信发送目标与您输入的手机号不一致");
        }

        MyUserDetails myUserDetails = myUserDetailsServiceMapper.findByUsername(mobileInRequest);
        if(Objects.isNull(myUserDetails)){
            throw new SessionAuthenticationException("您输入的手机号不是系统的注册用户");
        }

        session.removeAttribute("mobile_key");

    }


}
