package com.cai;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123"));
        boolean matches = passwordEncoder.matches("123", "$2a$10$SJI3Y25oxgw8XydepJzL6eY2paeMlpoFIVbDbgOit5sb8TudHwo3S");
        System.out.println(matches);
    }
}
