package com.cai.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 动态配置用户访问权限
 * 每次请求都会调用
 */
@Component("rbacService")
public class MyRBACService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    private MyRBACMapper myRBACMapper;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails) {
            List<String> menus = myRBACMapper.findMenuByUsername(((UserDetails) principal).getUsername());
            String uri = request.getRequestURI();
            return menus.stream().anyMatch(menu -> antPathMatcher.match(menu, uri));
        }
        return false;
    }

}
