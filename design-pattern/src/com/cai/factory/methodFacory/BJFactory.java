package com.cai.factory.methodFacory;

import com.cai.factory.simplefactory.BJCheesePizza;
import com.cai.factory.simplefactory.BJPepperPizza;
import com.cai.factory.simplefactory.Pizza;

public class BJFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String pizzaType) {
        return getPizza(pizzaType);
    }

    static Pizza getPizza(String pizzaType) {
        Pizza pizza = null;
        if(pizzaType.equals("cheese")){
            pizza = new BJCheesePizza();
            pizza.setName("北京奶酪披萨");
        }else if(pizzaType.equals("pepper")){
            pizza = new BJPepperPizza();
            pizza.setName("北京胡椒披萨");
        }
        return pizza;
    }
}
