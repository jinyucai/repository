package com.cai.decorator;

public class Sugger extends Decorator {


    @Override
    public void decorate() {
        super.setName("白砂糖");
        super.setPrice(1);
        System.out.println("白沙糖的价格是" + getPrice() + "元");
    }
}
