package com.cai.factory.simplefactory;

public class CheesePizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("给奶酪披萨准备奶酪");
    }
}
