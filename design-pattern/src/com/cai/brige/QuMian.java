package com.cai.brige;

public class QuMian extends Phone {

    private Brand brand;

    public QuMian(Brand brand){
        this.brand = brand;
    }

    @Override
    public void doSomething() {
        System.out.println("这是曲面手机");
        this.brand.open();
        this.brand.call();
        this.brand.close();
    }
}
