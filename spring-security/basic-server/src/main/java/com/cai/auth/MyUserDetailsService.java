package com.cai.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //加载用户信息
        MyUserDetails myUserDetails = myUserDetailsServiceMapper.findByUsername(username);
        //加载角色列表
        List<String> roleCodes = myUserDetailsServiceMapper.findRoleByUsername(username);
        //通过角色列表加载权限列表
        List<String> authorities = myUserDetailsServiceMapper.findAuthorityByRoleCodes(roleCodes);
        authorities.addAll(roleCodes);
        myUserDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", authorities)));
        return myUserDetails;
    }

}
