package com.cai.decorator;

public class NatieKoffe implements Coffe {

    private int price;
    public static final String NAME = "拿铁咖啡";

    private Decorator decorator;

    public NatieKoffe(Decorator decorator){
        this.decorator = decorator;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void cost() {
        setPrice(4);
        System.out.println("此咖啡的价格是" + getPrice() + "元");
        decorator.decorate();
        System.out.println("加了" + decorator.getName() + "的价格是" + (decorator.getPrice() + getPrice()));
    }

}
