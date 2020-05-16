package com.cai.factory.methodFacory;

import com.cai.factory.simplefactory.Pizza;

import java.util.Scanner;

public class OrderPizza2 {

    public OrderPizza2(AbsFactory absFactory){
        createPizza(absFactory);
    }

    private void createPizza(AbsFactory absFactory){
        Pizza pizza = null;
        do {
            String pizzaType = getPizzaType();
            pizza = absFactory.createPizza(pizzaType);
            if(pizza != null){
                pizza.prepare();
                pizza.beak();
                pizza.cut();
                pizza.beak();
            }else{
                System.out.println("订购失败");
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
