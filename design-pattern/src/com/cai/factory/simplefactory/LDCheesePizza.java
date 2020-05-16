package com.cai.factory.simplefactory;

public class LDCheesePizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("给伦敦奶酪披萨准备奶酪");
    }
}
