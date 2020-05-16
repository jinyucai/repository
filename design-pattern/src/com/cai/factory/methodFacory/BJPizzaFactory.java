package com.cai.factory.methodFacory;

import com.cai.factory.simplefactory.BJCheesePizza;
import com.cai.factory.simplefactory.BJPepperPizza;
import com.cai.factory.simplefactory.Pizza;

import static com.cai.factory.methodFacory.BJFactory.getPizza;

public class BJPizzaFactory extends OrderPizza {

    @Override
    public Pizza createPizza(String pizzaType) {
        return getPizza(pizzaType);
    }
}
