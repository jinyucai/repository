package com.cai.decorator;

public abstract class Decorator {

    private String name; // 调料名称

    private Integer price; // 调料价格

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void decorate(){

    }

}
