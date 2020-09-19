package com.cai.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailPassword implements UserDetailsPasswordService {


    @Override
    public UserDetails updatePassword(UserDetails userDetails, String s) {
        String password = userDetails.getPassword();

        return null;
    }


}
