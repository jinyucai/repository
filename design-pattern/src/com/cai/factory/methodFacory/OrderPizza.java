package com.cai.factory.methodFacory;

import com.cai.factory.simplefactory.Pizza;

import java.util.Scanner;

public abstract class OrderPizza {

    public abstract Pizza createPizza(String pizzaType);

    public OrderPizza(){
        Pizza pizza = null;
        String pizzaType = "";
        do {
            pizzaType = getPizzaType();
            pizza = createPizza(pizzaType);
            if(pizza != null){
                pizza.prepare();
                pizza.beak();
                pizza.cut();
                pizza.beak();
            }else{
                break;
            }
        }while (true);
    }

    private String getPizzaType(){
        System.out.println("choice pizza type");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        return next;
    }
}
