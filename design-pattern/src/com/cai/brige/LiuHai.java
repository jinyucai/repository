package com.cai.brige;

public class LiuHai extends Phone {

    private Brand brand;

    public LiuHai(Brand brand){
        this.brand = brand;
    }

    @Override
    public void doSomething() {
        System.out.println("这是刘海手机");
        this.brand.open();
        this.brand.call();
        this.brand.close();
    }
}
