package com.cai.controller;

import com.cai.auth.MyUserDetails;
import com.cai.auth.MyUserDetailsServiceMapper;
import com.cai.auth.sms.SmsCode;
import com.cai.utils.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SmsController {

    @Autowired
    private MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    @RequestMapping(value = "/smscode",method = RequestMethod.GET)
    public Map<String, Object> sms(@RequestParam String mobile, HttpSession session){
        HashMap<String, Object> r = new HashMap<>();
        MyUserDetails myUserDetails = myUserDetailsServiceMapper.findByUsername(mobile);
        if(myUserDetails == null){
            r.put("isok", false);
            r.put("message", "该手机号码还未注册");
            return r;
        }

        SmsCode smsCode = new SmsCode(RandomStringUtils.randomStringNumber(4), mobile, 60);

        System.out.println("模拟短信验证码====>" + smsCode.getCode());

        session.setAttribute("mobile_key",smsCode);
        r.put("isok", true);
        r.put("message", "发送短信成功");
        return r;
    }

}
