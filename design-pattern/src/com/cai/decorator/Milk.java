package com.cai.decorator;

public class Milk extends Decorator {


    @Override
    public void decorate() {
        super.setName("牛奶");
        super.setPrice(2);
        System.out.println("牛奶的价格是" + getPrice() + "元");
    }
}
