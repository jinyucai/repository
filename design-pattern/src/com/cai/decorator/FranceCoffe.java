package com.cai.decorator;

public class FranceCoffe implements Coffe {

    private int price;

    private Decorator decorator;

    public FranceCoffe(Decorator decorator){
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
        setPrice(8);
        System.out.println("此咖啡的价格是" + getPrice() + "元");
        decorator.decorate();
        System.out.println("加了" + decorator.getName() + "的价格是" + (decorator.getPrice() + getPrice()));
    }


}
