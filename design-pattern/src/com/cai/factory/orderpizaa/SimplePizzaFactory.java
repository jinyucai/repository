package com.cai.factory.orderpizaa;

import com.cai.factory.simplefactory.CheesePizza;
import com.cai.factory.simplefactory.GreekPizza;
import com.cai.factory.simplefactory.Pizza;

public class SimplePizzaFactory {

    public Pizza createPizza(String pizzaType){
        Pizza pizza = null;
        if(pizzaType.equals("greek")){
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        }else if(pizzaType.equals("cheese")){
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        }
        return pizza;
    }
}
