package com.cai.factory.orderpizaa;

public class OrderStore {
    public static void main(String[] args) {
//        new OrderPizza();
        new OrderPizza(new SimplePizzaFactory());
    }
}
