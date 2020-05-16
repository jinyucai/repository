package com.cai.factory.methodFacory;

import com.cai.factory.simplefactory.LDCheesePizza;
import com.cai.factory.simplefactory.LDPepperPizza;
import com.cai.factory.simplefactory.Pizza;

import static com.cai.factory.methodFacory.LDFactory.getPizza;

public class LDPizzaFactory extends OrderPizza {

    @Override
    public Pizza createPizza(String pizzaType) {
        return getPizza(pizzaType);
    }
}
