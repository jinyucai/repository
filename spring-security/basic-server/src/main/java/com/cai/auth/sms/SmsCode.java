package com.cai.auth.sms;

import java.time.LocalDateTime;

public class SmsCode {

    private String code;

    private String mobile;

    private LocalDateTime expire;

    public SmsCode(String code, String mobile, int expireTime) {
        this.code = code;
        this.mobile = mobile;
        this.expire = LocalDateTime.now().plusSeconds(60 * 2);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expire);
    }

    public String getCode() {
        return code;
    }

    public String getMobile() {
        return mobile;
    }

    public LocalDateTime getExpire() {
        return expire;
    }
}
