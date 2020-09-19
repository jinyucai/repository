package com.cai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.cai"})
public class BasicServerApp {

    public static void main(String[] args) {
        SpringApplication.run(BasicServerApp.class);
    }

}
