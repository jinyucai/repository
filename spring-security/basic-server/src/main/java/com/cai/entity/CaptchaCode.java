package com.cai.entity;

import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

public class CaptchaCode {
    //验证码数字
    private String code;
    //过期时间
    private LocalDateTime expireTime;

    public CaptchaCode(String code, int expireAfterSeconds) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return this.code;
    }

}
