package com.cai.factory.methodFacory;

import com.cai.factory.simplefactory.LDCheesePizza;
import com.cai.factory.simplefactory.LDPepperPizza;
import com.cai.factory.simplefactory.Pizza;

public class LDFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String pizzaType) {
        return getPizza(pizzaType);
    }

    static Pizza getPizza(String pizzaType) {
        Pizza pizza = null;
        if(pizzaType.equals("cheese")){
            pizza = new LDCheesePizza();
            pizza.setName("伦敦奶酪披萨");
        }else if(pizzaType.equals("pepper")){
            pizza = new LDPepperPizza();
            pizza.setName("伦敦胡椒披萨");
        }
        return pizza;
    }
}
